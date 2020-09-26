package primeDetectorSockets.primeDetector.validator;

import java.util.regex.Pattern;

public final class ValidatorUtil {

    private static final String IP_ADDRESS_REGEX =
            "^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$";
    private static final Pattern IP_PATTERN = Pattern.compile(IP_ADDRESS_REGEX);
    private String filepath;
    private int numThreads;
    private String persisterServiceIp;
    private int persisterServicePort;
    private int debugValue;
    private int capacity;


    public ValidatorUtil(String[] args) throws Exception {

        try{
            numThreads = Integer.parseInt(args[1]);
            capacity = Integer.parseInt(args[2]);
            debugValue = Integer.parseInt(args[5]);
            persisterServicePort = Integer.parseInt(args[4]);
            debugValue = Integer.parseInt(args[5]);
        }catch (NumberFormatException e){
            System.err.println("Incorrect number format! Accepts only Integers.");
        }
        filepath = args[0];
        persisterServiceIp = args[3];
        ValidatorUtil.validate("failed",ValidatorFetcher.fileNameValidator(this),
                ValidatorFetcher.debugValueValidator(this),
                ValidatorFetcher.numThreadsValidator(this),
                ValidatorFetcher.persisterServiceIpValidator(this));
    }
    public static void validate(String baseErrMsg, Validator... validators) throws Exception {
        for (Validator v : validators) {
            try {
                v.run();
            } catch (Exception e) {
                throw new Exception(baseErrMsg.concat(": " + e.getMessage()), e);
            }
        }

    }

    private static class ValidatorFetcher {
        public static Validator fileNameValidator(ValidatorUtil vu) {
            return new Validator() {
                @Override
                public void run() throws Exception {
                    if (vu.filepath == "") {
                        throw new Exception("File path cannot be empty!");
                    }
                    if(!vu.filepath.endsWith(".txt")){
                        throw new  Exception("File extension not txt!");
                    }
                }
            };
        }


        public static Validator debugValueValidator(ValidatorUtil vu) {
            return new Validator() {
                @Override
                public void run() throws Exception {
                    if (vu.debugValue < 0 || vu.debugValue > 4) {
                        throw new Exception("Debug Value out of range! Should be between 0 - 4");
                    }
                }
            };
        }

        public static Validator numThreadsValidator(ValidatorUtil vu) {
            return new Validator() {
                @Override
                public void run() throws Exception {
                    if (vu.numThreads < 1 || vu.numThreads > 5) {
                        throw new Exception("Num Threads out of range! Should be between 1 - 5");
                    }
                }
            };
        }

        public static Validator persisterServiceIpValidator(ValidatorUtil vu) {
            return new Validator() {
                @Override
                public void run() throws Exception {
                    if(vu.persisterServiceIp == null){
                        throw new Exception("IP cannot be null!");
                    }else if(!IP_PATTERN.matcher(vu.persisterServiceIp).matches()){
                        throw new Exception("IP addess format incorrect!");
                    }
                }
            };
        }

    }


}
