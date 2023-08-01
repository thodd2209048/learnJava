package manageEmployee;

public class Labor extends Employee {
    private LaborLevel level;
    private int baseCode;

    public Labor() {

    }


    public LaborLevel getLevel() {
        return level;
    }

    public void setLevel(LaborLevel level) {
        this.level = level;
    }

    public Labor(String name, int age, Gender gender, String address, LaborLevel level) {
        super(name, age, gender, address);
        this.level = level;
        this.baseCode = generateCode();
    }

    public double getSalary() {
        switch (level) {
            case LEVEL_1 -> {
                return 5 * 1;
            }
            case LEVEL_2 -> {
                return 5 * 2;
            }
            case LEVEL_3 -> {
                return 5 * 3;
            }
            case LEVEL_4 -> {
                return 5 * 4;
            }
            case LEVEL_5 -> {
                return 5 * 5;
            }
            case LEVEL_6 -> {
                return 6 * 6;
            }
            case LEVEL_7 -> {
                return 7 * 7;
            }
            case LEVEL_8 -> {
                return 8 * 8;
            }
            case LEVEL_9 -> {
                return 9 * 9;
            }
            case LEVEL_10 -> {
                return 10 * 10;
            }
            default -> System.out.println("Du lieu khong hop le");
        }
        return 0;
    }
}
