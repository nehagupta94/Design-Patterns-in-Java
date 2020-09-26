package replicaSystem.util;

import replicaSystem.myTree.Node;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TreeHelper {

    Map<Integer, Node> treeMap= new HashMap();
    static int REPLICA_ID = 0;
    Results results;

    public TreeHelper(Results results) {
        treeMap.put(REPLICA_ID++, null);
        treeMap.put(REPLICA_ID++, null);
        treeMap.put(REPLICA_ID++, null);
        this.results = results;
    }

    /**
     *
     * @param replicaId
     * @param bNumber
     * @param modify
     * This method is used to modify the data.
     */

    public void modifyData(int replicaId, int bNumber, Map<String,String> modify){
        Node repl = treeMap.get(replicaId);
        modify(repl, bNumber, modify);
        /*printDataInorder(repl);
        System.out.println();
        System.out.println();*/
    }

    /**
     *
     * @param root
     * @param bNumber
     * @param modify
     * @return
     * This method is used to modify the nodes of the trees.
     */

    private Node modify(Node root, int bNumber, Map<String,String> modify){
        boolean replace = false;
        if(root == null){
            return null;
        }
        if(bNumber < root.studentRecord.getbNumber()){
            modify(root.left, bNumber, modify);

        }
      if (bNumber > root.studentRecord.getbNumber()) {
            modify(root.right, bNumber, modify);

        }
        if (bNumber == root.studentRecord.getbNumber()) {
            if(modify.containsKey(root.studentRecord.getFirstName())){
                root.studentRecord.modifyFirstName(modify.get(root.studentRecord.getFirstName()));
                replace = true;
            }
            if(modify.containsKey(root.studentRecord.getMajor())){
                root.studentRecord.modifyMajor(modify.get(root.studentRecord.getMajor()));
                replace = true;
            }
            if(modify.containsKey(root.studentRecord.getLastName())){
                root.studentRecord.modifyLastName(modify.get(root.studentRecord.getLastName()));
                replace = true;
            }
            Set<String> set = StudentRecord.newSkill;
            //Set<String> set1 = set;
            for(String s : set){
                if(modify.containsKey(s)){
                    root.studentRecord.modifySkill(s, modify.get(s));
                    replace = true;
                }
            }
            if(replace){
                treeMap.get(0).notifyObserver(Action.MODIFY);
                treeMap.get(1).notifyObserver(Action.MODIFY);
                treeMap.get(2).notifyObserver(Action.MODIFY);
            }else {
                results.errorList.add("bNumber: " + bNumber + " Value to be replaced not found!");
            }
        }
        return root;
    }


    /**
     *
     * @param bNumber
     * @param firstName
     * @param lastName
     * @param gpa
     * @param major
     * @param skills
     * This method is used to insert the data.
     */

    public void insertData(int bNumber, String firstName, String lastName, Double gpa, String major, List<String> skills) {
            Node[] records = new Node[3];
            records[0] = new Node(new StudentRecord(bNumber, firstName, lastName, gpa, major, skills));
            for (int replicaId : treeMap.keySet()) {
                if (replicaId > 0)
                    records[replicaId] = records[0].clone();
                int i = replicaId-1;
                while (i >= 0) {
                    if (replicaId == i) continue;
                    records[replicaId].registerObserver(records[i]);
                    records[i].registerObserver(records[replicaId]);
                    i--;
                }
                if (treeMap.get(replicaId) == null)
                    treeMap.put(replicaId, records[replicaId]);
                else
                    insert(treeMap.get(replicaId), records[replicaId], skills);
            }
    }


     /* @param root
     * @param skills
     * @return
     * @throws CloneNotSupportedException
     */

    /**
     *
     * @param root
     * @param insertNode
     * @param skills
     * This method is used to insert the nodes.
     */

    private void insert(Node root, Node insertNode, List<String> skills) {
        if (insertNode.studentRecord.getbNumber() < root.studentRecord.getbNumber()) {
            if (root.left != null) insert(root.left, insertNode, skills);
            else root.left = insertNode;
        } else if (insertNode.studentRecord.getbNumber() > root.studentRecord.getbNumber()) {
            if (root.right != null) insert(root.right, insertNode, skills);
            else root.right = insertNode;
        } else if (insertNode.studentRecord.getbNumber() == root.studentRecord.getbNumber()) {
            root.studentRecord.setSkills(skills);

            treeMap.get(0).notifyObserver(Action.INSERT);
            treeMap.get(1).notifyObserver(Action.INSERT);
            treeMap.get(2).notifyObserver(Action.INSERT);
        }
    }

    /**
     *
     * @param result
     * @throws IOException
     * This method prints the data.
     */

    public void printData(Results result) throws IOException {
        //System.out.println("-----------------------");
        for (Node n : treeMap.values()) {
            /*System.out.println("n: " + n);
            System.out.println("result: " + result);*/
            printDataInorder(n,result);
            //System.out.println("-----------------------");
            result.writeToResultFile();
            break;
        }

    }

    /**
     *
     * @param node
     * @param result
     * This method prints the data in order.
     */

    private void printDataInorder(Node node, Results result){
        if(node != null){
            printDataInorder(node.left, result);
            result.resultArray.add(node.studentRecord.getbNumber()+":"+node.studentRecord.getFirstName()+","+node.studentRecord.getLastName()+","+
                    node.studentRecord.getGpa()+","+node.studentRecord.getMajor()+","+
                    node.studentRecord.getSkills());
            /*System.out.println("<<<<<<<<<<<<<------------------>>>>>>>>>>>>>>");
            System.out.println("bNumber " + node.studentRecord.getbNumber());
            System.out.println("firstName "+ node.studentRecord.getFirstName());
            System.out.println("lastName " + node.studentRecord.getLastName());
            System.out.println("gpa " + node.studentRecord.getGpa());
            System.out.println("major "+ node.studentRecord.getMajor());
            System.out.println("skills "+ node.studentRecord.getSkills());
            System.out.println("<<<<<<<<<<<<<------------------>>>>>>>>>>>>>>");*/
            printDataInorder(node.right, result);
        }
    }
}
