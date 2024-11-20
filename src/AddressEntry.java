public class AddressEntry 
{
    private String name;
    private String phoneNumber;
    private String address;
    private String email;

    public AddressEntry(String name, String phoneNumber, String address, String email)
    {
       this.name = name;
       this.phoneNumber = phoneNumber;
       this.address = address;
       this.email = email;
    }
    public String getName()
    {
        return name;
    }
    public String getAddress() 
    { 
        return address; 
    }
    public String getPhoneNumber() 
    { 
        return phoneNumber; 
    }
    public String getEmail() 
    { 
        return email; 
    }

    public void setName(String name) 
    { 
        this.name = name; 
    }
    public void setAddress(String address) 
    { 
        this.address = address; 
    }
    public void setPhoneNumber(String phoneNumber) 
    { 
        this.phoneNumber = phoneNumber; 
    }
    public void setEmail(String email) 
    { 
        this.email = email; 
    }
    public String toString() 
    {
        return "Name: " + name + ", Address: " + address + ", Phone: " + phoneNumber + ", Email: " + email;
    }


}
