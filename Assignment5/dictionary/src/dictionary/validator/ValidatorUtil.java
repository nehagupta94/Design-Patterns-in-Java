package validator;

/** Validator used for command line arguments and other validations */

public final class ValidatorUtil {
    private int k;
    private String inputFile;
    private String acceptWords;
    private String outputFile;

    public static void validate(String baseErrMsg, Validator... validators) throws Exception {
        for (Validator v : validators) {
            try {
                v.run();
            } catch (Exception e) {
                throw new Exception(baseErrMsg.concat(": " + e.getMessage()), e);
            }
        }
    }

    public ValidatorUtil(String[] args) throws Exception {
        try{
            k = Integer.parseInt(args[2]);
        }catch (NumberFormatException e){
            System.err.println("Exception: ");
            System.out.println("Exception: "+ e.getMessage().getClass().getName());
            e.printStackTrace();
        }
        inputFile = args[1];
        acceptWords = args[0];
        outputFile = args[3];
        ValidatorUtil.validate("failed",ValidatorFetcher.fileNameValidator(this),
                ValidatorFetcher.windowSizeValidator(this));
    }

    private static class ValidatorFetcher {
        public static Validator fileNameValidator(ValidatorUtil vu) {
            return new Validator() {
                @Override
                public void run() throws Exception {
                    if (vu.inputFile == "" || vu.acceptWords == "" || vu.outputFile == "") {
                        throw new Exception("File path cannot be empty!");
                    }
                    if (!vu.inputFile.endsWith(".txt") || !vu.acceptWords.endsWith(".txt") || !vu.outputFile.endsWith(".txt")) {
                        throw new Exception("File extension not txt!");
                    }
                }
            };
        }

        public static Validator windowSizeValidator(ValidatorUtil vu) {
            return new Validator() {
                @Override
                public void run() throws Exception {
                    if (vu.k <= 0) {
                        throw new Exception("k out of range! Should be > 0");
                    }
                }
            };
        }
    }
}
