public class Television {

    private String model;
    private String diagonal;

    public void setDiagonal(String diagonal) {
        this.diagonal = diagonal;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Television() {

    }

    @Override
    public String toString() {
        return "Television{" +
                "model='" + model + '\'' +
                ", diagonal='" + diagonal + '\'' +
                '}';
    }
}
