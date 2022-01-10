package test.authentication;

public class LoginCred {
    private String email;
    private String password;
    private Job job;

    public LoginCred(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LoginCred() {

    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public static class Job{
        public String getPosition() {
            return position;
        }

        private String position;

        @Override
        public String toString() {
            return "Job{" +
                    "position='" + position + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LoginCred{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", job=" + job +
                '}';
    }
}
