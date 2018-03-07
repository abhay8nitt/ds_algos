package Trie;

class Contact {
    private String phone;
    private String name;
    private String email;

    public Contact(String phone, String name, String email) {
        this.phone = phone;
        this.name = name;
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Contact{" +
                "phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
public class ContactTrie extends Trie{

    public void insert(final Contact contact){
        TrieNode _phone = super.insert(contact.getPhone());
        _phone.contact = contact;
        TrieNode _contactName = super.insert(contact.getName());
        _contactName.contact = contact;
    }

    @Override
    protected void print(TrieNode node, String word) {
        if(node.isWord()){
            System.out.println(node.contact);
        }
    }
}