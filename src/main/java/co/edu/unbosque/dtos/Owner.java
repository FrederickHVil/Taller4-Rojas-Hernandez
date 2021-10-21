package co.edu.unbosque.dtos;

public class Owner {
    private String username;
    private int person_id;
    private String name;
    private String address;
    private String neighborhood;

    private Owner(String username, int person_id, String name, String address, String neighborhood){
        this.username=username;
        this.person_id=person_id;
        this.name=name;
        this.address=address;
        this.neighborhood=neighborhood;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }
}
