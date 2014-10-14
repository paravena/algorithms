package movies;

import org.junit.Test;

public class SocialMovies2ImplTest {
    @Test
    public void test() {
        SocialMovies2Impl sm = new SocialMovies2Impl();
        sm.addCustomer(1, "pablo");
        sm.addCustomer(2, "renato");
        sm.addCustomer(3, "peeman");
        sm.addCustomer(4, "vitoko");
        sm.addCustomer(5, "caxero");
        sm.addCustomer(6, "panchito");
        sm.addCustomer(7, "gonzalito");
        sm.addCustomer(8, "joven ivan");
        sm.addFriend(1, 3);
        sm.addFriend(1, 4);
        sm.addFriend(1, 6);
        sm.addFriend(5, 3);
        sm.addFriend(3, 4);
        sm.addFriend(4, 2);
        sm.addFriend(2, 7);
        sm.addFriend(7, 8);
        sm.bfs(1);
        System.out.println("gonzalito distance: " + sm.customers.get(7).getDistance());
        System.out.println("caxero distance: " + sm.customers.get(5).getDistance());
        System.out.println("renato distance: " + sm.customers.get(2).getDistance());
        System.out.println("joven ivan distance: " + sm.customers.get(8).getDistance());
    }
}
