import java.util.Scanner;
public class AplikasiManajemenPetshop {
    public static void main(String[] args) {
        Scanner intscan = new Scanner(System.in); //Dibuat untuk angka
        Scanner scanner = new Scanner(System.in); //Dibuat untuk huruf

        System.out.print("Masukkan jumlah maksimal pelanggan: ");
        int maxCustomers = intscan.nextInt();
        System.out.print("Masukkan jumlah maksimal produk: ");
        int maxProducts = intscan.nextInt();

        PetShop petShop = new PetShop(maxCustomers, maxProducts);

        // Menambahkan beberapa pelanggan dan hewan peliharaan
        System.out.println("\n=== Menambahkan Pelanggan ===");
        for (int i = 0; i < maxCustomers; i++) {
            System.out.println("Pelanggan ke-" + (i + 1));
            System.out.print("Nama: ");
            String name = scanner.nextLine();
            System.out.print("Alamat: ");
            String address = scanner.nextLine();

            Customer customer = new Customer(name, address, maxCustomers);
            petShop.addCustomer(customer);

            System.out.println();
        }

        // Menambahkan beberapa hewan peliharaan
        System.out.println("\n=== Menambahkan Hewan Peliharaan ===");
        Customer[] customers = petShop.getCustomers();
        for (int i = 0; i < maxCustomers; i++) {
            Customer customer = petShop.getCustomer(customers[i].getName());
            System.out.println("Pelanggan: " + customer.getName());
            System.out.println("Hewan Peliharaan: ");

            System.out.print("Nama: ");
            String petName = scanner.nextLine();
            System.out.print("Jenis: ");
            String species = scanner.nextLine();

            Pet pet = new Pet(petName, species);
            customer.addPet(pet);
            System.out.println();

        }

        // Menambahkan beberapa produk
        System.out.println("\n=== Menambahkan Produk ===");
        for (int i = 0; i < maxProducts; i++) { //For langkah-langkah untuk menambahkan pelanggan
            System.out.println("Produk ke-" + (i + 1));
            System.out.print("Nama: ");
            String productName = scanner.nextLine();
            System.out.print("Harga: ");
            double price = intscan.nextDouble(); //Tipe data dari variabel price

            Product product = new Product(productName, price);
            petShop.addProduct(product);

            System.out.println();
        }
        scanner.close(); //Menutup objek Scanner setelah selesai membaca input

        // Menampilkan data pelanggan dan hewan peliharaan
        System.out.println("\n=== Data Pelanggan dan Hewan Peliharaan ===");
        for (int i = 0; i < maxCustomers; i++) {
            Customer customer = petShop.getCustomer(customers[i].getName());
            System.out.println("Nama Pelanggan: " + customer.getName());
            System.out.println("Alamat Pelanggan: " + customer.getAddress());
            System.out.println("Hewan Peliharaan:");
            for (Pet pet : customer.getPets()) {
                if (pet != null) {
                    System.out.println("- Nama: " + pet.getName());
                    System.out.println("  Jenis: " + pet.getSpecies());
                }
            }
            System.out.println();
        }

        // Menampilkan daftar produk
        System.out.println("\n=== Daftar Produk ===");
        for (Product product : petShop.getProducts()) {
            if (product != null) { //memeriksa apakah suatu variabel dengan nama "product" memiliki nilai yang tidak null atau tidak terdefinisi
                //nilai dari variabel "product" tidak null, maka blok kode di dalam kurung kurawal {} setelah kondisi akan dieksekusi
                System.out.println("Nama: " + product.getName());
                System.out.println("    Harga: " + product.getPrice());
                System.out.println();
            }
        }
    }
}
class Pet {
    private String name;
    private String species;

    public Pet(String name, String species) {
        this.name = name; //This kata kunci yang digunakan untuk merujuk pada objek
        this.species = species;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }
}

class Customer {
    //menunjukkan bahwa Anda mendeklarasikan sebuah variabel dengan tipe data String yang memiliki aksesibilitas private
    private String name;
    private String address;
    private Pet[] pets;
    private int numPets; //menunjukkan bahwa Anda mendeklarasikan sebuah variabel dengan tipe data int yang memiliki aksesibilitas private

    public Customer(String name, String address, int maxPets) {
        this.name = name;
        this.address = address;
        this.pets = new Pet[maxPets];
        this.numPets = 0;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Pet[] getPets() {
        return pets;
    }

    public void addPet(Pet pet) {
        if (numPets < pets.length) {
            pets[numPets] = pet;
            numPets++;
            System.out.println("Hewan peliharaan berhasil ditambahkan.");
        } else {
            System.out.println("Batas jumlah hewan peliharaan telah tercapai.");
        }
    }
}

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class PetShop {
    private Customer[] customers;
    private Product[] products;
    private int numCustomers;
    private int numProducts;

    public PetShop(int maxCustomers, int maxProducts) {
        this.customers = new Customer[maxCustomers];
        this.products = new Product[maxProducts];
        this.numCustomers = 0;
        this.numProducts = 0;
    }

    public void addCustomer(Customer customer) {
        if (numCustomers < customers.length) {
            customers[numCustomers] = customer;
            numCustomers++;
            System.out.println("Pelanggan berhasil ditambahkan.");
        } else {
            System.out.println("Batas jumlah pelanggan telah tercapai.");
        }
    }

    public Customer[] getCustomers() {
        return customers;
    }

    public Customer getCustomer(String name) {
        for (int i = 0; i < numCustomers; i++) {
            if (customers[i].getName().equals(name)) {
                return customers[i];
            }
        }
        return null; //mengembalikan nulai null dari sebuah variabel
    }

    public void addProduct(Product product) {
        if (numProducts < products.length) { //kondisi yang memeriksa apakah numProducts (jumlah produk saat ini) lebih kecil dari panjang array products
            //Jika kondisi ini benar, berarti masih terdapat slot kosong dalam array.
            products[numProducts] = product;
            numProducts++; //untuk menambahkan 1 ke nilai numProducts
            System.out.println("Produk berhasil ditambahkan.");
        } else {
            System.out.println("Batas jumlah produk telah tercapai.");
        }
    }

    public Product[] getProducts() {
        return products;
    }
}