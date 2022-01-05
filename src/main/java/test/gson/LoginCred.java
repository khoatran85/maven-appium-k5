package test.gson;

public class LoginCred {
    private String username;
    private String password;
    private Job job;

    public LoginCred() {

    }

    public Job getJob() {
        return job;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginCred(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", job=" + job +
                '}';
    }
}
