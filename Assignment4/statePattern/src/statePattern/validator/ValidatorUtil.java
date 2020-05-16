package validator;

/** Validator used for command line arguments and other validations */

public final class ValidatorUtil {
    private int windowSize;
    private String filepathItems;
    private String filepathInput;
    private String outputFile;

    public ValidatorUtil(String[] args) throws Exception {
        try{
            windowSize = Integer.parseInt(args[2]);
        }catch (NumberFormatException e){
            System.err.println("Exception: ");
            System.out.println("Exception: "+ e.getMessage().getClass().getName());
            e.printStackTrace();
        }
        filepathItems = args[1];
        filepathInput = args[0];
        outputFile = args[3];
        ValidatorUtil.validate("failed",ValidatorFetcher.fileNameValidator(this),
                ValidatorFetcher.windowSizeValidator(this));
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
                    if (vu.filepathItems == "" || vu.filepathInput == "") {
                        throw new Exception("File path cannot be empty!");
                    }
                    if (!vu.filepathItems.endsWith(".txt") || !vu.filepathInput.endsWith(".txt") || !vu.outputFile.endsWith(".txt")) {
                        throw new Exception("File extension not txt!");
                    }
                }
            };
        }

        public static Validator windowSizeValidator(ValidatorUtil vu) {
            return new Validator() {
                @Override
                public void run() throws Exception {
                    if (vu.windowSize <= 0) {
                        throw new Exception("Window Size out of range! Should be > 0");
                    }
                }
            };
        }

    }
}
