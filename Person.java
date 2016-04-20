class Person {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Person(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    String name;
    Integer id;
}