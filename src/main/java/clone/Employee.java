package clone;

public class Employee implements Cloneable {

    private int id;
    private String name;
    private Address address;
    private double sal;

    public Employee(int id, String name, Address address, double sal) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.sal = sal;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getSal() {
        return sal;
    }

    public void setSal(double sal) {
        this.sal = sal;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", sal=" + sal +
                '}';
    }
}


class Address {
    private String adress1;
    private String adress2;
    private String city;
    private String country;

    public Address(String adress1, String adress2, String city, String country) {
        this.adress1 = adress1;
        this.adress2 = adress2;
        this.city = city;
        this.country = country;
    }

    public String getAdress1() {
        return adress1;
    }

    public void setAdress1(String adress1) {
        this.adress1 = adress1;
    }

    public String getAdress2() {
        return adress2;
    }

    public void setAdress2(String adress2) {
        this.adress2 = adress2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "adress1='" + adress1 + '\'' +
                ", adress2='" + adress2 + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}