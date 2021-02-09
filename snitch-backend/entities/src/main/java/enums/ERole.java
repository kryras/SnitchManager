package enums;

public enum ERole {
    ROLE_USER,
    ROLE_MODERATOR,
    ROLE_ADMIN;
    @Override
    public String toString(){
        switch(this) {
            case ROLE_USER:
                return "User";
            case ROLE_MODERATOR:
                return "Moderator";
            case ROLE_ADMIN:
                return "Admin";
            default:
                return "";
        }
    }
}
