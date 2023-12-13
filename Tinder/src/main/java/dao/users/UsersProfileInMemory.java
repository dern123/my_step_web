package dao.users;

import java.util.ArrayList;
import java.util.List;

public class UsersProfileInMemory  {
    private List<UserProfile> profiles;

    public UsersProfileInMemory() {
        profiles = new ArrayList<>();

        profiles = new ArrayList<>();
        profiles.add(new UserProfile(1, "John",
                "https://hips.hearstapps.com/hmg-prod/images/gettyimages-936360206.jpg"));
        profiles.add(new UserProfile(2, "Jane",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm48hud8Rmxfcr21GAjCeqJ2PfS-foEjPbcQ&usqp=CAU"));
        profiles.add(new UserProfile(3, "Megan",
                "https://muz-tv.ru/storage/images/news/crops/news-page/2nFodlsFVKxT1549Tx109ijmpgvZ07dCL3AjM3Xa.jpg"));
        profiles.add(new UserProfile(4, "Jack",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTikkG-i4_acoYfiWR9tT45zIWcnDDVKLKbeA&usqp=CAU"));
        profiles.add(new UserProfile(5, "Madison",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5f/Madison_Beer_2019_by_Glenn_Francis_%28cropped%29.jpg/1200px-Madison_Beer_2019_by_Glenn_Francis_%28cropped%29.jpg"));
        profiles.add(new UserProfile(6, "Nicole",
                "https://hips.hearstapps.com/hmg-prod/images/nicole_kidman_photo_stuart_c_wilson_getty_images_693045658_profile.jpg?crop=1xw:0.9823182711198428xh;center,top&resize=640:*"));
        profiles.add(new UserProfile(7, "Elizabeth",
                "https://karsh.org/wordpress/wp-content/uploads/2022/01/Yousuf-Karsh-Princess-Elizabeth-1951-h_03285929-252x300.jpg"));
        profiles.add(new UserProfile(8, "Bella",
                "https://hollywoodlife.com/wp-content/uploads/2017/08/bio-pic-bella-hadid.jpg?quality=100"));
        profiles.add(new UserProfile(9, "Edward",
                "https://a1cf74336522e87f135f-2f21ace9a6cf0052456644b80fa06d4f.ssl.cf2.rackcdn.com/images/characters/large/800/Edward-Cullen.Twilight.webp"));
        profiles.add(new UserProfile(10, "Jacob",
                "https://upload.wikimedia.org/wikipedia/en/c/cf/Jacob_Black2.jpg"));
        profiles.add(new UserProfile(11, "Bella",
                "https://avatars.dzeninfra.ru/get-zen_doc/5234501/pub_610e84e6ac2ddc55eb9a43b3_610e86d5ab0c947a46448900/scale_1200"));
        profiles.add(new UserProfile(12, "Jason",
                "https://hips.hearstapps.com/hmg-prod/images/jason-statham-attends-the-press-conference-of-director-f-gary-grays-film-the-fate-of-the-furious-on-march-23-2017-in-beijing-china-photo-by-vcg_vcg-via-getty-images-square.jpg"));
        profiles.add(new UserProfile(13, "Alice",
                "https://i.pinimg.com/1200x/07/85/60/07856007efb34020a1dd32eee435aab8.jpg"));
        profiles.add(new UserProfile(14, "Gal",
                "https://f12.pmo.ee/af0nVlaG9jikBEk2G1Qxkz7B2Ss=/1200x630/filters:focal(1353x1023:1500x1155)/nginx/o/2023/10/10/15646398t1habd6.jpg"));
        profiles.add(new UserProfile(15, "Brian",
                "https://hellomagrussia.ru/uploads/vin-dizely-nameknul-chto-pol-uoker-mozhet-poyavitysya-v-novom-forsazhe_44584_cover_752x544.webp"));
        profiles.add(new UserProfile(16, "Gerard",
                "https://upload.wikimedia.org/wikipedia/commons/5/53/Gerard_Butler_%2829681162176%29.jpg"));
        profiles.add(new UserProfile(17, "Denzel",
                "https://www.themoviedb.org/t/p/w500/jj2Gcobpopokal0YstuCQW0ldJ4.jpg"));
        profiles.add(new UserProfile(18, "Greta",
                "https://www.kinonews.ru/insimgs/newsimg/newsimg17814.jpg"));
        profiles.add(new UserProfile(19, "Hansel",
                "https://i.pinimg.com/736x/59/7f/17/597f17a846b177750724eb536f5abdb1.jpg"));
        profiles.add(new UserProfile(20, "Liam",
                "https://cdn.aarp.net/content/dam/aarp/entertainment/celebrities/2022/03/1140-liam-neeson-looking-to-side.jpg"));
        profiles.add(new UserProfile(21, "Jessica",
                "https://hips.hearstapps.com/hmg-prod/images/jessica-chastain-gettyimages-492294551.jpg?crop=1xw:0.75xh;center,top&resize=1200:*"));
    }


    //метод для доступа к элементу конкретному
    public UserProfile get(int index) {
        return profiles.get(index);
    }
//
//    @Override
//    public List<UserProfile> getAll() {
//        return profiles;
//    }
//
//    @Override
//    public UserProfile getById(int id) {
//        return profiles.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
//    }
//
//    @Override
//    public void add(UserProfile item) {
//        profiles.add(item);
//    }
//
//    @Override
//    public void update(UserProfile item) {
//        int index = profiles.indexOf(item);
//        if (index != -1) {
//            profiles.set(index, item);
//        }
//    }
//
//    @Override
//    public void delete(int id) {
//        profiles.removeIf(profile -> profile.getId() == id);
//    }
}
