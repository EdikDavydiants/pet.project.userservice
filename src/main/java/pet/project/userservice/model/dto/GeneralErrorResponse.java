package pet.project.userservice.model.dto;

public record GeneralErrorResponse(

        int status,

        String type,

        String message,

        String details
) {
    public static GeneralErrorResponseBuilder builder() {
        return new GeneralErrorResponseBuilder();
    }

    public static class GeneralErrorResponseBuilder {
        private int status;
        private String type;
        private String message;
        private String details;

        GeneralErrorResponseBuilder() {
        }

        public GeneralErrorResponseBuilder status(int status) {
            this.status = status;
            return this;
        }

        public GeneralErrorResponseBuilder type(String type) {
            this.type = type;
            return this;
        }

        public GeneralErrorResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public GeneralErrorResponseBuilder details(String details) {
            this.details = details;
            return this;
        }

        public GeneralErrorResponse build() {
            return new GeneralErrorResponse(this.status, this.type, this.message, this.details);
        }

        public String toString() {
            return "GeneralErrorResponse.GeneralErrorResponseBuilder(status=" + this.status + ", type=" + this.type + ", message=" + this.message + ", details=" + this.details + ")";
        }
    }
}
