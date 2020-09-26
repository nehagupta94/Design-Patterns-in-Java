package primeDetectorSockets.persisterService.validator;


public final class ValidatorUtil {

    private int port;
    private String outputFile;


    public ValidatorUtil(String[] args) throws Exception {

        try{
            port = Integer.parseInt(args[0]);
        }catch (NumberFormatException e){
            System.err.println("Incorrect number format! Accepts only Integers.");
        }
        outputFile = args[1];
        ValidatorUtil.validate("failed", ValidatorFetcher.fileNameValidator(this),
                ValidatorFetcher.portValidator(this));
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
                    if (vu.outputFile == "") {
                        throw new Exception("File name cannot be empty!");
                    }
                    if(!vu.outputFile.endsWith(".txt")){
                        throw new  Exception("File extension not txt!");
                    }
                }
            };
        }

        public static Validator portValidator(ValidatorUtil vu) {
            return new Validator() {
                @Override
                public void run() throws Exception {
                    if (vu.port < 1 || vu.port > 65535) {
                        throw new Exception("Invalid port number!");
                    }
                }
            };
        }
    }


}
