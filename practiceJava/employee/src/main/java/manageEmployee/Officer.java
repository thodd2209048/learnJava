package manageEmployee;

public class Officer extends Employee{
    private int Exp;
    private int baseCode;


    public int getExp() {
        return Exp;
    }

    public void setExp(int exp) {
        Exp = exp;
    }

    public Officer(String name, int age, Gender gender, String address, int exp) {
        super(name, age, gender, address);
        Exp = exp;
//        this.baseCode = generateCode();
    }

    public double getSalary() {
        if (Exp <= 2) {
            return 8;
        } else if (Exp <= 5) {
            return 15;
        } else if (Exp <= 10) {
            return  25;
        } else {
            return  35;
        }
    }
}
