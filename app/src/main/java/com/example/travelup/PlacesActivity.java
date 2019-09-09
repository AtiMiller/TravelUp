package com.example.travelup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class PlacesActivity extends AppCompatActivity {

    //Declarations
    AnimationDrawable animationDrawableSummer;
    LinearLayout summerLay;
    ActionBar actionBar;
    Button btnApply;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    LinearLayoutManager layoutManager;
    ImageView help;
    Button close;
    FragmentManager manager;
    Fragment helpFrag;

    //ArrayList For RecyclerView

    PlaceAdapter placeAdapter;
    private List<Place> AllPlaces;


    //    ListView listView;
    ScrollView scrollView;
    CheckBox check_spring, check_summer, check_autumn, check_winter, check_africa, check_asia, check_europe,
            check_north_america, check_south_america, check_austria_oceania;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        allPlacesList();
        buildRecyclerView();

        //        HelpFragment

        manager = getSupportFragmentManager();
        helpFrag = manager.findFragmentById(R.id.helpFrag);
        manager.beginTransaction()
                .hide(helpFrag)
                .commit();

        help = findViewById(R.id.Help);

        close = findViewById(R.id.close);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.beginTransaction()
                        .hide(manager.findFragmentById(R.id.helpFrag))
                        .commit();
            }
        });

        //Apply Button

        btnApply = findViewById(R.id.btnApply);

        //Layout FindView

        summerLay = findViewById(R.id.summer_lay);

        //ScrollView

        scrollView = findViewById(R.id.scrollview);

        //SelectSeason CheckBox

        check_spring = findViewById(R.id.check_spring);
        check_summer = findViewById(R.id.check_summer);
        check_autumn = findViewById(R.id.check_autumn);
        check_winter = findViewById(R.id.check_winter);

        //SelectContinent CheckBox

        check_africa = findViewById(R.id.check_africa);
        check_asia = findViewById(R.id.check_asia);
        check_europe = findViewById(R.id.check_europe);
        check_north_america = findViewById(R.id.check_north_america);
        check_south_america = findViewById(R.id.check_south_america);
        check_austria_oceania = findViewById(R.id.check_austria_oceania);


        //ActionBar

        actionBar = getSupportActionBar();
        actionBar.setTitle("Places");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //AnimationBackground

        animationDrawableSummer = (AnimationDrawable) summerLay.getBackground();
        animationDrawableSummer.setEnterFadeDuration(2000);
        animationDrawableSummer.setExitFadeDuration(4000);
        animationDrawableSummer.start();

        //checkBox SetOnClickListener Declarations

//        check_autumn.setOnCheckedChangeListener(this);

//        This is for the SWITCH statement.
//        check_summer.setOnClickListener(this);
//        check_spring.setOnClickListener(this);
//        check_south_america.setOnClickListener(this);
//        check_north_america.setOnClickListener(this);
//        check_europe.setOnClickListener(this);
//        check_austria_oceania.setOnClickListener(this);
//        check_asia.setOnClickListener(this);
//        check_africa.setOnClickListener(this);
//        check_winter.setOnClickListener(this);
//        check_autumn.setOnClickListener(this);

//       btnApply.setOnClickListener((View.OnClickListener) PlacesActivity.this);


        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(check_spring.isChecked() && check_summer.isChecked() && check_autumn.isChecked() && check_winter.isChecked())
                {
                    allPlacesList();

                }
                else if (!(check_spring.isChecked()) && !(check_summer.isChecked()) && !(check_autumn.isChecked()) && !(check_winter.isChecked()))
                {
                    allPlacesList();
                }
                else if (check_spring.isChecked() && check_summer.isChecked() && check_autumn.isChecked())
                {
                    addSpringSummerAutumn();
                }
                else if (check_summer.isChecked() && check_autumn.isChecked() && check_winter.isChecked())
                {
                    addSummerAutumnWinter();
                }
                else if (check_spring.isChecked() && check_autumn.isChecked() && check_winter.isChecked())
                {
                    addSpringAutumnWinter();
                }
                else if (check_spring.isChecked() && check_summer.isChecked() && check_winter.isChecked())
                {
                    addSpringSummerWinter();
                }
                else if (check_spring.isChecked() && check_summer.isChecked() )
                {
                    addSpringSummer();
                }
                else if (check_spring.isChecked() && check_autumn.isChecked() )
                {
                    addSpringAutumn();
                }
                else if (check_spring.isChecked() && check_winter.isChecked() )
                {
                    addSpringWinter();
                }
                else if (check_summer.isChecked() && check_autumn.isChecked() )
                {
                    addSummerAutumn();
                }
                else if (check_summer.isChecked() && check_winter.isChecked() )
                {
                    addSummerWinter();
                }
                else if (check_autumn.isChecked() && check_winter.isChecked() )
                {
                    addAutumnWinter();
                }
                else if (check_spring.isChecked())
                {
                    addSpring();
                }
                else if (check_summer.isChecked())
                {
                    addSummer();
                }
                else if (check_autumn.isChecked())
                {
                    addAutumn();
                }
                else if (check_winter.isChecked())
                {
                    addWinter();
                }

//                CONTINENTS

                else if(check_africa.isChecked() && check_asia.isChecked() && check_austria_oceania.isChecked()
                && check_europe.isChecked() && check_north_america.isChecked() &&check_south_america.isChecked())
                {
                    allPlacesList();
                }
                else if(check_africa.isChecked() && check_asia.isChecked() && check_europe.isChecked()
                        && check_north_america.isChecked() &&check_south_america.isChecked())
                {
                    Toast.makeText(PlacesActivity.this, "Sorry. This option is not available yet.", Toast.LENGTH_SHORT).show();
                }
                else if(check_africa.isChecked() && check_asia.isChecked() && check_austria_oceania.isChecked()
                        && check_europe.isChecked() && check_north_america.isChecked())
                {
                    Toast.makeText(PlacesActivity.this, "Sorry. This option is not available yet.", Toast.LENGTH_SHORT).show();
                }
                else if(check_africa.isChecked() && check_asia.isChecked() && check_austria_oceania.isChecked()
                        && check_europe.isChecked() &&check_south_america.isChecked())
                {
                    Toast.makeText(PlacesActivity.this, "Sorry. This option is not available yet.", Toast.LENGTH_SHORT).show();
                }
                else if(check_africa.isChecked() && check_asia.isChecked() && check_austria_oceania.isChecked()
                        && check_north_america.isChecked() &&check_south_america.isChecked())
                {
                    Toast.makeText(PlacesActivity.this, "Sorry. This option is not available yet.", Toast.LENGTH_SHORT).show();
                }
                else if(check_africa.isChecked() && check_austria_oceania.isChecked()
                        && check_europe.isChecked() && check_north_america.isChecked() &&check_south_america.isChecked())
                {
                    Toast.makeText(PlacesActivity.this, "Sorry. This option is not available yet.", Toast.LENGTH_SHORT).show();
                }
                else if(check_austria_oceania.isChecked()
                        && check_europe.isChecked() && check_north_america.isChecked() &&check_south_america.isChecked())
                {
                    Toast.makeText(PlacesActivity.this, "Sorry. This option is not available yet.", Toast.LENGTH_SHORT).show();
                }
                else if(check_austria_oceania.isChecked() && check_asia.isChecked()
                        && check_europe.isChecked() && check_north_america.isChecked() &&check_south_america.isChecked())
                {
                    Toast.makeText(PlacesActivity.this, "Sorry. This option is not available yet.", Toast.LENGTH_SHORT).show();
                }
                else if(check_austria_oceania.isChecked() && check_asia.isChecked()
                        && check_europe.isChecked() && check_north_america.isChecked() &&check_south_america.isChecked())
                {
                    Toast.makeText(PlacesActivity.this, "Sorry. This option is not available yet.", Toast.LENGTH_SHORT).show();
                }
                else if (check_austria_oceania.isChecked())
                {
                    addAustralia();
                }
                else if (check_africa.isChecked())
                {
                    addAfrica();
                }
                else if(check_europe.isChecked())
                {
                    addEurope();
                }
                else if(check_asia.isChecked())
                {
                    addAsia();
                }
                else if(check_north_america.isChecked())
                {
                    addNorthAmerica();
                }
                else if (check_south_america.isChecked())
                {
                    addSouthAmerica();
                }
                else
                {
                    allPlacesList();
                }
            }
        });


    }



//    SEASONS FILTER

    public void addSummer()
    {
        AllPlaces.clear();
        AllPlaces.add(new Place(
                "Istanbul, Turkey",
                "This magical meeting place of East and West has more top-drawer attractions than it has minarets (and that's a lot).",
                "http://blog.radissonblu.com/wp-content/uploads/2017/07/Blue-Mosque.jpg",
                "https://flagpedia.net/data/flags/w580/tr.png"));
        AllPlaces.add(new Place(
                "Rome, Italy",
                "A heady mix of haunting ruins, awe-inspiring art and vibrant street life, Italy’s hot-blooded capital is one of the world’s most romantic and charismatic cities.",
                "https://cdn.citywonders.com/media/17764/rome-city.jpg?anchor=center&mode=crop&width=1200&height=600",
                "https://flagpedia.net/data/flags/w580/it.png"));
        AllPlaces.add(new Place(
                "Berlin, Germany",
                "Berlin's combo of glamour and grit is bound to mesmerise all those keen to explore its vibrant culture, cutting-edge architecture, fabulous food, intense parties and tangible history.",
                "https://www.berlin-welcomecard.de/sites/default/files/styles/stage_desktop_20x/public/berlin-welcomecard_hero_2880__1.jpg?itok=fgbQwjEO&timestamp=1528123533",
                "https://flagpedia.net/data/flags/ultra/de.png"));
        AllPlaces.add(new Place(
                "Paris, France",
                "Paris' monument-lined boulevards, museums, classical bistros and boutiques are enhanced by a new wave of multimedia galleries, creative wine bars, design shops and tech start-ups.",
                "https://www.fodors.com/wp-content/uploads/2018/10/HERO_UltimateParis_Heroshutterstock_112137761.jpg",
                "https://flagpedia.net/data/flags/ultra/fr.png"));
        AllPlaces.add(new Place(
                "Lisbon, Portugal",
                "Seven cinematic hillsides overlooking the Rio Tejo cradle Lisbon's postcard-perfect panorama of cobbled alleyways, ancient ruins and white-domed cathedrals a captivating scene crafted over centuries.",
                "https://www.lisbonguru.com/wp-content/uploads/2015/11/viewpoints-lisbon.jpg",
                "https://flagpedia.net/data/flags/ultra/pt.png"));
        AllPlaces.add(new Place(
                "Madrid, Spain",
                "Madrid is a beguiling place with an energy that carries one simple message: this city really knows how to live.",
                "https://avisassets.abgemea.com/dam/jcr:afda6cf9-fc35-4c47-940a-baa594ab04ed/Grand_Via_Madrid_Credit_iStock_kasto80.jpg",
                "https://flagpedia.net/data/flags/ultra/es.png"));
        AllPlaces.add(new Place(
                "London, United Kingdom",
                "Immersed in history, London's rich seams of eye-opening antiquity are everywhere.",
                "http://qarva.com/wp-content/gallery/ott-2018/london-background-14.jpeg",
                "https://flagpedia.net/data/flags/ultra/gb.png"));
        AllPlaces.add(new Place(
                "Santorini, Greece",
                "Santorini is the supermodel of the Greek islands, a head-turner whose face is instantly recognisable around the world: multicoloured cliffs soar out of a sea-drowned caldera, topped by drifts of whitewashed buildings.",
                "https://dak95nwic4sny.cloudfront.net/73/santorini-44228168-1550653710-ImageGalleryLightboxLarge.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Flag_of_Greece.svg/2000px-Flag_of_Greece.svg.png"));
        adapter.notifyDataSetChanged();
    }

    public void addSpring()
    {
        AllPlaces.clear();
        AllPlaces.add(new Place(
                "Skopje, Macedonia",
                "Skopje has plenty of charm. Its Ottoman- and Byzantine-era sights are focused around the city's delightful Čaršija, bordered by the 15th-century Kameni Most (Stone Bridge) and Tvrdina Kale Fortress – Skopje's guardian since the 5th century.",
                "https://lonelyplanetimages.imgix.net/mastheads/83405857%20.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/mk.png"));
        AllPlaces.add(new Place(
                "Pristina, Kosovo",
                "Pristina is a fast-changing city that feels full of optimism and potential, even if its traffic-clogged streets and mismatched architectural styles don't make it an obviously attractive place.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/e228bf3be784ffff7a338ec8d9167d30-pristina.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/ks.png"));
        AllPlaces.add(new Place(
                "Tirana, Albania",
                "Lively, colourful Tirana is where this tiny nation's hopes and dreams coalesce into a vibrant whirl of traffic, brash consumerism and unfettered fun.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/25e80d94ce0c6dfee0634a2b8598feae-tirana.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/al.png"));
        AllPlaces.add(new Place(
                "Podgorica, Montenegro",
                "Given it’s undergone five name changes, passed through the hands of everyone from the Romans to the Turks to the Austro-Hungarians, and twice been wiped off the map entirely, it’s little wonder that Podgorica (Подгорица) seems permanently gripped by an identity crisis.",
                "https://lonelyplanetimages.imgix.net/mastheads/147031066_medium.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/me.png"));
        AllPlaces.add(new Place(
                "Sarajevo, Bosnia Herzegovina",
                "Ringed by mountains, Sarajevo is a singular city with an enticing East-meets-West vibe all of its own. It was once renowned as a religious melting pot, earning it the epithet 'the Jerusalem of Europe'.",
                "https://s23835.pcdn.co/wp-content/uploads/2018/07/Bosnia-Sarajevo-view-4.jpg",
                "https://flagpedia.net/data/flags/w580/ba.png"));
        AllPlaces.add(new Place(
                "Belgrade, Serbia",
                "Outspoken, adventurous, proud and audacious: Belgrade ('White City') is by no means a 'pretty' capital, but its gritty exuberance makes it one of Europe's most happening cities.",
                "https://media.architecturaldigest.com/photos/5d37554e47a77b000872bbf4/16:9/w_1280,c_limit/GettyImages-847708760.jpg",
                "https://flagpedia.net/data/flags/w580/rs.png"));
        AllPlaces.add(new Place(
                "Zagreb, Croatia",
                "Zagreb is made for strolling. Wander through the Upper Town's red-roof and cobblestone glory, peppered with church spires.",
                "https://brightcove04pmdo-a.akamaihd.net/5104226627001/5104226627001_5441113691001_5428833703001-vs.jpg?pubId=5104226627001&videoId=5428833703001",
                "https://flagpedia.net/data/flags/w580/hr.png"));
        AllPlaces.add(new Place(
                "Ljubljana, Slovenia",
                "Slovenia's capital and largest city is one of Europe's greenest and most liveable capitals; it was the European Commission's Green Capital of Europe in 2016.",
                "https://lonelyplanetimages.imgix.net/mastheads/GettyImages-494314513_high.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/si.png"));
        AllPlaces.add(new Place(
                "Sofia, Bulgaria",
                "Bulgaria's pleasingly laid-back capital is often overlooked by visitors heading to the coast or the ski resorts, but they're missing something special.",
                "https://happytowander.com/wp-content/uploads/Sofia-Bulgaria-Happy-to-Wander-0609.jpg",
                "https://flagpedia.net/data/flags/w580/bg.png"));

        adapter.notifyDataSetChanged();
    }

    public void addAutumn()
    {
        AllPlaces.clear();

        AllPlaces.add(new Place(
                "Chisinau, Moldova",
                "Pretty much all roads in Moldova lead to its wine-and-food loving capital.",
                "https://d1z15fh6odiy9s.cloudfront.net/plonk/adfb.../adfba780ee49dd02aa788772e39b7b44497517c80233d3e1cbad1c138f694a02-large.jpg",
                "https://flagpedia.net/data/flags/w580/md.png"));
        AllPlaces.add(new Place(
                "Bucharest, Romania",
                "Romania’s capital sometimes gets a bad rap, but in fact it's dynamic, energetic and lots of fun. Many travellers give the city just a night or two before heading off to Transylvania, but that’s not enough time.",
                "https://www.romania-insider.com/sites/default/files/styles/article_large_image/public/2019-02/bucharest_universitatii_square_-_shutterstock.jpg",
                "https://flagpedia.net/data/flags/w580/ro.png"));
        AllPlaces.add(new Place(
                "Budapest, Hungary",
                "Budapest has something for everyone – from dramatic history and flamboyant architecture to healing thermal waters and a nightlife that is unrivalled in Eastern and Central Europe.",
                "https://brightcove04pmdo-a.akamaihd.net/5104226627001/5104226627001_5515999951001_5214933106001-vs.jpg?pubId=5104226627001&videoId=5214933106001",
                "https://flagpedia.net/data/flags/w580/hu.png"));
        AllPlaces.add(new Place(
                "Bern, Switzerland",
                "Wandering through the picture-postcard, Unesco World Heritage–listed Old Town, with its provincial, laid-back air, it's hard to believe that Bern (Berne in French) is the capital of Switzerland.",
                "https://d2mpqlmtgl1znu.cloudfront.net/AcuCustom/Sitename/DAM/017/Bern_Adobe.jpg",
                "https://flagpedia.net/data/flags/w580/ch.png"));
        AllPlaces.add(new Place(
                "Vienna, Austria",
                "Baroque streetscapes and imperial palaces set the stage for Vienna's artistic and musical masterpieces alongside its coffee-house culture and vibrant epicurean and design scenes.",
                "https://img.theculturetrip.com/x/smart/wp-content/uploads/2019/04/eur-austria-vienna.jpg",
                "https://flagpedia.net/data/flags/w580/at.png"));
        AllPlaces.add(new Place(
                "Bratislava, Slovakia",
                "Slovakia's capital since the country's independence in 1993, Bratislava is a mosaic of illustrious history: a medieval and Gothic old town, baroque palaces commissioned by Hungarian nobles, and the crowning castle, rebuilt to Renaissance finery.",
                "https://s27363.pcdn.co/wp-content/uploads/2018/08/Bratislava-Slovakia-1129x752.jpg.optimal.jpg",
                "https://flagpedia.net/data/flags/w580/sk.png"));
        AllPlaces.add(new Place(
                "Prague, Czech Republic",
                "Prague is the equal of Paris in terms of beauty. Its history goes back a millennium. And the beer? The best in Europe.",
                "https://cdn.thecrazytourist.com/wp-content/uploads/2018/06/ccimage-shutterstock_428804917.jpg",
                "https://flagpedia.net/data/flags/w580/cz.png"));
        AllPlaces.add(new Place(
                "Kiev, Ukraine",
                "Long before Ukraine and Russia existed, the city's inhabitants were already striding up and down the green hills, idling hot afternoons away on the Dnipro River and promenading along Khreshchatyk then a stream, now the main avenue.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/a6469f3993128cbcdde6e2b0a90aa1d3-kyiv.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/ua.png"));
        AllPlaces.add(new Place(
                "Warsaw, Poland",
                "A phoenix arisen from the ashes, Poland's capital impresses with its resilience, respect for history, contemporary style and sheer joie de vivre.",
                "https://www.roughguides.com/wp-content/uploads/2013/04/warsaw-old-town-royal-castle-poland-shutterstock_1171262353.jpg",
                "https://flagpedia.net/data/flags/w580/pl.png"));
        AllPlaces.add(new Place(
                "Minsk, Belarus",
                "Minsk will almost certainly surprise you. The capital of Belarus is, contrary to its dreary reputation, a progressive, modern and clean place.",
                "https://lonelyplanetimages.imgix.net/mastheads/513008517_large.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/by.png"));
        AllPlaces.add(new Place(
                "Vilnius, Lithuania",
                "There is a dreamy quality to Vilnius (vil-nyus), especially in the golden glow of a midsummer evening.",
                "https://content.r9cdn.net/rimg/dimg/09/c0/92c9d67d-city-7110-162f78f1fbc.jpg?crop=true&width=1000&height=600&xhint=2126&yhint=1263",
                "https://flagpedia.net/data/flags/w580/lt.png"));
        AllPlaces.add(new Place(
                "Riga, Latvia",
                "The Gothic spires that dominate Rīga's cityscape might suggest austerity, but it is the flamboyant art nouveau that forms the flesh and the spirit of this vibrant cosmopolitan city, the largest of all three Baltic capitals.",
                "https://www.roadaffair.com/wp-content/uploads/2019/03/house-blackheads-saint-peters-church-riga-latvia-shutterstock_1070326685-1024x683.jpg",
                "https://flagpedia.net/data/flags/w580/lv.png"));
        AllPlaces.add(new Place(
                "Tallinn, Estonia",
                "No longer the plaything of greater powers – Danish, Swedish, Polish, German and Soviet – Tallinn is now a proud European capital with an allure all of its own.",
                "https://www.nomadepicureans.com/wp-content/uploads/2019/05/Tallinn-Walking-Tour-Feature-Image.jpg",
                "https://flagpedia.net/data/flags/w580/ee.png"));
        adapter.notifyDataSetChanged();
    }

    public void addWinter()
    {
        AllPlaces.clear();
        AllPlaces.add(new Place(
                "Helsinki, Finland",
                "Entwined with the Baltic's bays, inlets and islands, Helsinki's boulevards and backstreets overflow with magnificent architecture, intriguing drinking and dining venues and groundbreaking design.",
                "http://www.hel.fi/wps/wcm/connect/5a20f5c2-6516-40e7-a239-ef6585fdfcfd/englkielisetpalvelutuutiskuva.jpg?MOD=AJPERES&CACHEID=ROOTWORKSPACE-5a20f5c2-6516-40e7-a239-ef6585fdfcfd-mz18k6W",
                "https://flagpedia.net/data/flags/w580/fi.png"));
        AllPlaces.add(new Place(
                "Stockholm, Sweden",
                "Stockholmers call their city 'beauty on water'. But despite the well-preserved historic core, Stockholm is no museum piece: it's modern, dynamic and ever-evolving.",
                "https://a.cdn-hotels.com/gdcs/production151/d1898/abc9d800-c31d-11e8-825c-0242ac110006.jpg",
                "https://flagpedia.net/data/flags/w580/se.png"));
        AllPlaces.add(new Place(
                "Reykjavik, Iceland",
                "The world’s most northerly capital combines colourful buildings, quirky, creative people, eye-popping design, wild nightlife and a capricious soul.",
                "https://www.roughguides.com/wp-content/uploads/2013/01/hallgrimskirkja-cathedral-city-reykjavik-iceland-shutterstock_613997816.jpg",
                "https://flagpedia.net/data/flags/w580/is.png"));
        AllPlaces.add(new Place(
                "Oslo, Norway",
                "Surrounded by mountains and sea, this compact, cultured and fun city has a palpable sense of reinvention.",
                "https://cdn-image.travelandleisure.com/sites/default/files/styles/1600x1000/public/1498156851/opera-house-oslo-norway-OSLO0617.jpg?itok=rcuoL2da",
                "https://flagpedia.net/data/flags/w580/no.png"));
        AllPlaces.add(new Place(
                "Copenhagen, Denmark",
                "Copenhagen is the epitome of Scandi cool. Modernist lamps light New Nordic tables, bridges buzz with cycling commuters and eye-candy locals dive into pristine waterways.",
                "https://lonelyplanetimages.imgix.net/mastheads/GettyImages-126140612_super%20.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/dk.png"));
        AllPlaces.add(new Place(
                "Amsterdam, Netherlands",
                "Golden Age canals lined by tilting gabled buildings are the backdrop for Amsterdam's treasure-packed museums, vintage-filled shops and hyper-creative drinking, dining and design scenes.",
                "http://cdn.cnn.com/cnnnext/dam/assets/190315131613-07-netherlands-happiest-coutries-2018.jpg",
                "https://flagpedia.net/data/flags/w580/nl.png"));
        AllPlaces.add(new Place(
                "Dublin, Ireland",
                "A small capital with a huge reputation, Dublin has a mix of heritage and hedonism that will not disappoint. All you have to do is show up.",
                "https://thesefootballtimes.co/wp-content/uploads/2015/10/Dublin-Ireland.jpg",
                "https://flagpedia.net/data/flags/w580/ie.png"));
        AllPlaces.add(new Place(
                "Brussels, Belgium",
                "Historic yet hip, bureaucratic yet bizarre, self-confident yet unshowy, Brussels is multicultural to its roots.",
                "https://media.timeout.com/images/105237721/image.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/Flag_of_Belgium.svg/2000px-Flag_of_Belgium.svg.png"));
        adapter.notifyDataSetChanged();
    }

//    10 SEASON COMBINATIONS

    public void addSpringSummerAutumn()
    {
        AllPlaces.clear();
        AllPlaces.add(new Place(
                "Skopje, Macedonia",
                "Skopje has plenty of charm. Its Ottoman- and Byzantine-era sights are focused around the city's delightful Čaršija, bordered by the 15th-century Kameni Most (Stone Bridge) and Tvrdina Kale Fortress – Skopje's guardian since the 5th century.",
                "https://lonelyplanetimages.imgix.net/mastheads/83405857%20.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/mk.png"));
        AllPlaces.add(new Place(
                "Pristina, Kosovo",
                "Pristina is a fast-changing city that feels full of optimism and potential, even if its traffic-clogged streets and mismatched architectural styles don't make it an obviously attractive place.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/e228bf3be784ffff7a338ec8d9167d30-pristina.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/ks.png"));
        AllPlaces.add(new Place(
                "Tirana, Albania",
                "Lively, colourful Tirana is where this tiny nation's hopes and dreams coalesce into a vibrant whirl of traffic, brash consumerism and unfettered fun.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/25e80d94ce0c6dfee0634a2b8598feae-tirana.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/al.png"));
        AllPlaces.add(new Place(
                "Podgorica, Montenegro",
                "Given it’s undergone five name changes, passed through the hands of everyone from the Romans to the Turks to the Austro-Hungarians, and twice been wiped off the map entirely, it’s little wonder that Podgorica (Подгорица) seems permanently gripped by an identity crisis.",
                "https://lonelyplanetimages.imgix.net/mastheads/147031066_medium.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/me.png"));
        AllPlaces.add(new Place(
                "Sarajevo, Bosnia Herzegovina",
                "Ringed by mountains, Sarajevo is a singular city with an enticing East-meets-West vibe all of its own. It was once renowned as a religious melting pot, earning it the epithet 'the Jerusalem of Europe'.",
                "https://s23835.pcdn.co/wp-content/uploads/2018/07/Bosnia-Sarajevo-view-4.jpg",
                "https://flagpedia.net/data/flags/w580/ba.png"));
        AllPlaces.add(new Place(
                "Belgrade, Serbia",
                "Outspoken, adventurous, proud and audacious: Belgrade ('White City') is by no means a 'pretty' capital, but its gritty exuberance makes it one of Europe's most happening cities.",
                "https://media.architecturaldigest.com/photos/5d37554e47a77b000872bbf4/16:9/w_1280,c_limit/GettyImages-847708760.jpg",
                "https://flagpedia.net/data/flags/w580/rs.png"));
        AllPlaces.add(new Place(
                "Zagreb, Croatia",
                "Zagreb is made for strolling. Wander through the Upper Town's red-roof and cobblestone glory, peppered with church spires.",
                "https://brightcove04pmdo-a.akamaihd.net/5104226627001/5104226627001_5441113691001_5428833703001-vs.jpg?pubId=5104226627001&videoId=5428833703001",
                "https://flagpedia.net/data/flags/w580/hr.png"));
        AllPlaces.add(new Place(
                "Ljubljana, Slovenia",
                "Slovenia's capital and largest city is one of Europe's greenest and most liveable capitals; it was the European Commission's Green Capital of Europe in 2016.",
                "https://lonelyplanetimages.imgix.net/mastheads/GettyImages-494314513_high.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/si.png"));
        AllPlaces.add(new Place(
                "Sofia, Bulgaria",
                "Bulgaria's pleasingly laid-back capital is often overlooked by visitors heading to the coast or the ski resorts, but they're missing something special.",
                "https://happytowander.com/wp-content/uploads/Sofia-Bulgaria-Happy-to-Wander-0609.jpg",
                "https://flagpedia.net/data/flags/w580/bg.png"));
        AllPlaces.add(new Place(
                "Istanbul, Turkey",
                "This magical meeting place of East and West has more top-drawer attractions than it has minarets (and that's a lot).",
                "http://blog.radissonblu.com/wp-content/uploads/2017/07/Blue-Mosque.jpg",
                "https://flagpedia.net/data/flags/w580/tr.png"));
        AllPlaces.add(new Place(
                "Rome, Italy",
                "A heady mix of haunting ruins, awe-inspiring art and vibrant street life, Italy’s hot-blooded capital is one of the world’s most romantic and charismatic cities.",
                "https://cdn.citywonders.com/media/17764/rome-city.jpg?anchor=center&mode=crop&width=1200&height=600",
                "https://flagpedia.net/data/flags/w580/it.png"));
        AllPlaces.add(new Place(
                "Berlin, Germany",
                "Berlin's combo of glamour and grit is bound to mesmerise all those keen to explore its vibrant culture, cutting-edge architecture, fabulous food, intense parties and tangible history.",
                "https://www.berlin-welcomecard.de/sites/default/files/styles/stage_desktop_20x/public/berlin-welcomecard_hero_2880__1.jpg?itok=fgbQwjEO&timestamp=1528123533",
                "https://flagpedia.net/data/flags/ultra/de.png"));
        AllPlaces.add(new Place(
                "Paris, France",
                "Paris' monument-lined boulevards, museums, classical bistros and boutiques are enhanced by a new wave of multimedia galleries, creative wine bars, design shops and tech start-ups.",
                "https://www.fodors.com/wp-content/uploads/2018/10/HERO_UltimateParis_Heroshutterstock_112137761.jpg",
                "https://flagpedia.net/data/flags/ultra/fr.png"));
        AllPlaces.add(new Place(
                "Lisbon, Portugal",
                "Seven cinematic hillsides overlooking the Rio Tejo cradle Lisbon's postcard-perfect panorama of cobbled alleyways, ancient ruins and white-domed cathedrals a captivating scene crafted over centuries.",
                "https://www.lisbonguru.com/wp-content/uploads/2015/11/viewpoints-lisbon.jpg",
                "https://flagpedia.net/data/flags/ultra/pt.png"));
        AllPlaces.add(new Place(
                "Madrid, Spain",
                "Madrid is a beguiling place with an energy that carries one simple message: this city really knows how to live.",
                "https://avisassets.abgemea.com/dam/jcr:afda6cf9-fc35-4c47-940a-baa594ab04ed/Grand_Via_Madrid_Credit_iStock_kasto80.jpg",
                "https://flagpedia.net/data/flags/ultra/es.png"));
        AllPlaces.add(new Place(
                "London, United Kingdom",
                "Immersed in history, London's rich seams of eye-opening antiquity are everywhere.",
                "http://qarva.com/wp-content/gallery/ott-2018/london-background-14.jpeg",
                "https://flagpedia.net/data/flags/ultra/gb.png"));
        AllPlaces.add(new Place(
                "Santorini, Greece",
                "Santorini is the supermodel of the Greek islands, a head-turner whose face is instantly recognisable around the world: multicoloured cliffs soar out of a sea-drowned caldera, topped by drifts of whitewashed buildings.",
                "https://dak95nwic4sny.cloudfront.net/73/santorini-44228168-1550653710-ImageGalleryLightboxLarge.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Flag_of_Greece.svg/2000px-Flag_of_Greece.svg.png"));
        AllPlaces.add(new Place(
                "Chisinau, Moldova",
                "Pretty much all roads in Moldova lead to its wine-and-food loving capital.",
                "https://d1z15fh6odiy9s.cloudfront.net/plonk/adfb.../adfba780ee49dd02aa788772e39b7b44497517c80233d3e1cbad1c138f694a02-large.jpg",
                "https://flagpedia.net/data/flags/w580/md.png"));
        AllPlaces.add(new Place(
                "Bucharest, Romania",
                "Romania’s capital sometimes gets a bad rap, but in fact it's dynamic, energetic and lots of fun. Many travellers give the city just a night or two before heading off to Transylvania, but that’s not enough time.",
                "https://www.romania-insider.com/sites/default/files/styles/article_large_image/public/2019-02/bucharest_universitatii_square_-_shutterstock.jpg",
                "https://flagpedia.net/data/flags/w580/ro.png"));
        AllPlaces.add(new Place(
                "Budapest, Hungary",
                "Budapest has something for everyone – from dramatic history and flamboyant architecture to healing thermal waters and a nightlife that is unrivalled in Eastern and Central Europe.",
                "https://brightcove04pmdo-a.akamaihd.net/5104226627001/5104226627001_5515999951001_5214933106001-vs.jpg?pubId=5104226627001&videoId=5214933106001",
                "https://flagpedia.net/data/flags/w580/hu.png"));
        AllPlaces.add(new Place(
                "Bern, Switzerland",
                "Wandering through the picture-postcard, Unesco World Heritage–listed Old Town, with its provincial, laid-back air, it's hard to believe that Bern (Berne in French) is the capital of Switzerland.",
                "https://d2mpqlmtgl1znu.cloudfront.net/AcuCustom/Sitename/DAM/017/Bern_Adobe.jpg",
                "https://flagpedia.net/data/flags/w580/ch.png"));
        AllPlaces.add(new Place(
                "Vienna, Austria",
                "Baroque streetscapes and imperial palaces set the stage for Vienna's artistic and musical masterpieces alongside its coffee-house culture and vibrant epicurean and design scenes.",
                "https://img.theculturetrip.com/x/smart/wp-content/uploads/2019/04/eur-austria-vienna.jpg",
                "https://flagpedia.net/data/flags/w580/at.png"));
        AllPlaces.add(new Place(
                "Bratislava, Slovakia",
                "Slovakia's capital since the country's independence in 1993, Bratislava is a mosaic of illustrious history: a medieval and Gothic old town, baroque palaces commissioned by Hungarian nobles, and the crowning castle, rebuilt to Renaissance finery.",
                "https://s27363.pcdn.co/wp-content/uploads/2018/08/Bratislava-Slovakia-1129x752.jpg.optimal.jpg",
                "https://flagpedia.net/data/flags/w580/sk.png"));
        AllPlaces.add(new Place(
                "Prague, Czech Republic",
                "Prague is the equal of Paris in terms of beauty. Its history goes back a millennium. And the beer? The best in Europe.",
                "https://cdn.thecrazytourist.com/wp-content/uploads/2018/06/ccimage-shutterstock_428804917.jpg",
                "https://flagpedia.net/data/flags/w580/cz.png"));
        AllPlaces.add(new Place(
                "Kiev, Ukraine",
                "Long before Ukraine and Russia existed, the city's inhabitants were already striding up and down the green hills, idling hot afternoons away on the Dnipro River and promenading along Khreshchatyk then a stream, now the main avenue.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/a6469f3993128cbcdde6e2b0a90aa1d3-kyiv.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/ua.png"));
        AllPlaces.add(new Place(
                "Warsaw, Poland",
                "A phoenix arisen from the ashes, Poland's capital impresses with its resilience, respect for history, contemporary style and sheer joie de vivre.",
                "https://www.roughguides.com/wp-content/uploads/2013/04/warsaw-old-town-royal-castle-poland-shutterstock_1171262353.jpg",
                "https://flagpedia.net/data/flags/w580/pl.png"));
        AllPlaces.add(new Place(
                "Minsk, Belarus",
                "Minsk will almost certainly surprise you. The capital of Belarus is, contrary to its dreary reputation, a progressive, modern and clean place.",
                "https://lonelyplanetimages.imgix.net/mastheads/513008517_large.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/by.png"));
        AllPlaces.add(new Place(
                "Vilnius, Lithuania",
                "There is a dreamy quality to Vilnius (vil-nyus), especially in the golden glow of a midsummer evening.",
                "https://content.r9cdn.net/rimg/dimg/09/c0/92c9d67d-city-7110-162f78f1fbc.jpg?crop=true&width=1000&height=600&xhint=2126&yhint=1263",
                "https://flagpedia.net/data/flags/w580/lt.png"));
        AllPlaces.add(new Place(
                "Riga, Latvia",
                "The Gothic spires that dominate Rīga's cityscape might suggest austerity, but it is the flamboyant art nouveau that forms the flesh and the spirit of this vibrant cosmopolitan city, the largest of all three Baltic capitals.",
                "https://www.roadaffair.com/wp-content/uploads/2019/03/house-blackheads-saint-peters-church-riga-latvia-shutterstock_1070326685-1024x683.jpg",
                "https://flagpedia.net/data/flags/w580/lv.png"));
        AllPlaces.add(new Place(
                "Tallinn, Estonia",
                "No longer the plaything of greater powers – Danish, Swedish, Polish, German and Soviet – Tallinn is now a proud European capital with an allure all of its own.",
                "https://www.nomadepicureans.com/wp-content/uploads/2019/05/Tallinn-Walking-Tour-Feature-Image.jpg",
                "https://flagpedia.net/data/flags/w580/ee.png"));
        adapter.notifyDataSetChanged();
    }

    public void addSummerAutumnWinter()
    {
        AllPlaces.clear();
        AllPlaces.add(new Place(
                "Istanbul, Turkey",
                "This magical meeting place of East and West has more top-drawer attractions than it has minarets (and that's a lot).",
                "http://blog.radissonblu.com/wp-content/uploads/2017/07/Blue-Mosque.jpg",
                "https://flagpedia.net/data/flags/w580/tr.png"));
        AllPlaces.add(new Place(
                "Rome, Italy",
                "A heady mix of haunting ruins, awe-inspiring art and vibrant street life, Italy’s hot-blooded capital is one of the world’s most romantic and charismatic cities.",
                "https://cdn.citywonders.com/media/17764/rome-city.jpg?anchor=center&mode=crop&width=1200&height=600",
                "https://flagpedia.net/data/flags/w580/it.png"));
        AllPlaces.add(new Place(
                "Berlin, Germany",
                "Berlin's combo of glamour and grit is bound to mesmerise all those keen to explore its vibrant culture, cutting-edge architecture, fabulous food, intense parties and tangible history.",
                "https://www.berlin-welcomecard.de/sites/default/files/styles/stage_desktop_20x/public/berlin-welcomecard_hero_2880__1.jpg?itok=fgbQwjEO&timestamp=1528123533",
                "https://flagpedia.net/data/flags/ultra/de.png"));
        AllPlaces.add(new Place(
                "Paris, France",
                "Paris' monument-lined boulevards, museums, classical bistros and boutiques are enhanced by a new wave of multimedia galleries, creative wine bars, design shops and tech start-ups.",
                "https://www.fodors.com/wp-content/uploads/2018/10/HERO_UltimateParis_Heroshutterstock_112137761.jpg",
                "https://flagpedia.net/data/flags/ultra/fr.png"));
        AllPlaces.add(new Place(
                "Lisbon, Portugal",
                "Seven cinematic hillsides overlooking the Rio Tejo cradle Lisbon's postcard-perfect panorama of cobbled alleyways, ancient ruins and white-domed cathedrals a captivating scene crafted over centuries.",
                "https://www.lisbonguru.com/wp-content/uploads/2015/11/viewpoints-lisbon.jpg",
                "https://flagpedia.net/data/flags/ultra/pt.png"));
        AllPlaces.add(new Place(
                "Madrid, Spain",
                "Madrid is a beguiling place with an energy that carries one simple message: this city really knows how to live.",
                "https://avisassets.abgemea.com/dam/jcr:afda6cf9-fc35-4c47-940a-baa594ab04ed/Grand_Via_Madrid_Credit_iStock_kasto80.jpg",
                "https://flagpedia.net/data/flags/ultra/es.png"));
        AllPlaces.add(new Place(
                "London, United Kingdom",
                "Immersed in history, London's rich seams of eye-opening antiquity are everywhere.",
                "http://qarva.com/wp-content/gallery/ott-2018/london-background-14.jpeg",
                "https://flagpedia.net/data/flags/ultra/gb.png"));
        AllPlaces.add(new Place(
                "Santorini, Greece",
                "Santorini is the supermodel of the Greek islands, a head-turner whose face is instantly recognisable around the world: multicoloured cliffs soar out of a sea-drowned caldera, topped by drifts of whitewashed buildings.",
                "https://dak95nwic4sny.cloudfront.net/73/santorini-44228168-1550653710-ImageGalleryLightboxLarge.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Flag_of_Greece.svg/2000px-Flag_of_Greece.svg.png"));
        AllPlaces.add(new Place(
                "Chisinau, Moldova",
                "Pretty much all roads in Moldova lead to its wine-and-food loving capital.",
                "https://d1z15fh6odiy9s.cloudfront.net/plonk/adfb.../adfba780ee49dd02aa788772e39b7b44497517c80233d3e1cbad1c138f694a02-large.jpg",
                "https://flagpedia.net/data/flags/w580/md.png"));
        AllPlaces.add(new Place(
                "Bucharest, Romania",
                "Romania’s capital sometimes gets a bad rap, but in fact it's dynamic, energetic and lots of fun. Many travellers give the city just a night or two before heading off to Transylvania, but that’s not enough time.",
                "https://www.romania-insider.com/sites/default/files/styles/article_large_image/public/2019-02/bucharest_universitatii_square_-_shutterstock.jpg",
                "https://flagpedia.net/data/flags/w580/ro.png"));
        AllPlaces.add(new Place(
                "Budapest, Hungary",
                "Budapest has something for everyone – from dramatic history and flamboyant architecture to healing thermal waters and a nightlife that is unrivalled in Eastern and Central Europe.",
                "https://brightcove04pmdo-a.akamaihd.net/5104226627001/5104226627001_5515999951001_5214933106001-vs.jpg?pubId=5104226627001&videoId=5214933106001",
                "https://flagpedia.net/data/flags/w580/hu.png"));
        AllPlaces.add(new Place(
                "Bern, Switzerland",
                "Wandering through the picture-postcard, Unesco World Heritage–listed Old Town, with its provincial, laid-back air, it's hard to believe that Bern (Berne in French) is the capital of Switzerland.",
                "https://d2mpqlmtgl1znu.cloudfront.net/AcuCustom/Sitename/DAM/017/Bern_Adobe.jpg",
                "https://flagpedia.net/data/flags/w580/ch.png"));
        AllPlaces.add(new Place(
                "Vienna, Austria",
                "Baroque streetscapes and imperial palaces set the stage for Vienna's artistic and musical masterpieces alongside its coffee-house culture and vibrant epicurean and design scenes.",
                "https://img.theculturetrip.com/x/smart/wp-content/uploads/2019/04/eur-austria-vienna.jpg",
                "https://flagpedia.net/data/flags/w580/at.png"));
        AllPlaces.add(new Place(
                "Bratislava, Slovakia",
                "Slovakia's capital since the country's independence in 1993, Bratislava is a mosaic of illustrious history: a medieval and Gothic old town, baroque palaces commissioned by Hungarian nobles, and the crowning castle, rebuilt to Renaissance finery.",
                "https://s27363.pcdn.co/wp-content/uploads/2018/08/Bratislava-Slovakia-1129x752.jpg.optimal.jpg",
                "https://flagpedia.net/data/flags/w580/sk.png"));
        AllPlaces.add(new Place(
                "Prague, Czech Republic",
                "Prague is the equal of Paris in terms of beauty. Its history goes back a millennium. And the beer? The best in Europe.",
                "https://cdn.thecrazytourist.com/wp-content/uploads/2018/06/ccimage-shutterstock_428804917.jpg",
                "https://flagpedia.net/data/flags/w580/cz.png"));
        AllPlaces.add(new Place(
                "Kiev, Ukraine",
                "Long before Ukraine and Russia existed, the city's inhabitants were already striding up and down the green hills, idling hot afternoons away on the Dnipro River and promenading along Khreshchatyk then a stream, now the main avenue.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/a6469f3993128cbcdde6e2b0a90aa1d3-kyiv.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/ua.png"));
        AllPlaces.add(new Place(
                "Warsaw, Poland",
                "A phoenix arisen from the ashes, Poland's capital impresses with its resilience, respect for history, contemporary style and sheer joie de vivre.",
                "https://www.roughguides.com/wp-content/uploads/2013/04/warsaw-old-town-royal-castle-poland-shutterstock_1171262353.jpg",
                "https://flagpedia.net/data/flags/w580/pl.png"));
        AllPlaces.add(new Place(
                "Minsk, Belarus",
                "Minsk will almost certainly surprise you. The capital of Belarus is, contrary to its dreary reputation, a progressive, modern and clean place.",
                "https://lonelyplanetimages.imgix.net/mastheads/513008517_large.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/by.png"));
        AllPlaces.add(new Place(
                "Vilnius, Lithuania",
                "There is a dreamy quality to Vilnius (vil-nyus), especially in the golden glow of a midsummer evening.",
                "https://content.r9cdn.net/rimg/dimg/09/c0/92c9d67d-city-7110-162f78f1fbc.jpg?crop=true&width=1000&height=600&xhint=2126&yhint=1263",
                "https://flagpedia.net/data/flags/w580/lt.png"));
        AllPlaces.add(new Place(
                "Riga, Latvia",
                "The Gothic spires that dominate Rīga's cityscape might suggest austerity, but it is the flamboyant art nouveau that forms the flesh and the spirit of this vibrant cosmopolitan city, the largest of all three Baltic capitals.",
                "https://www.roadaffair.com/wp-content/uploads/2019/03/house-blackheads-saint-peters-church-riga-latvia-shutterstock_1070326685-1024x683.jpg",
                "https://flagpedia.net/data/flags/w580/lv.png"));
        AllPlaces.add(new Place(
                "Tallinn, Estonia",
                "No longer the plaything of greater powers – Danish, Swedish, Polish, German and Soviet – Tallinn is now a proud European capital with an allure all of its own.",
                "https://www.nomadepicureans.com/wp-content/uploads/2019/05/Tallinn-Walking-Tour-Feature-Image.jpg",
                "https://flagpedia.net/data/flags/w580/ee.png"));
        AllPlaces.add(new Place(
                "Helsinki, Finland",
                "Entwined with the Baltic's bays, inlets and islands, Helsinki's boulevards and backstreets overflow with magnificent architecture, intriguing drinking and dining venues and groundbreaking design.",
                "http://www.hel.fi/wps/wcm/connect/5a20f5c2-6516-40e7-a239-ef6585fdfcfd/englkielisetpalvelutuutiskuva.jpg?MOD=AJPERES&CACHEID=ROOTWORKSPACE-5a20f5c2-6516-40e7-a239-ef6585fdfcfd-mz18k6W",
                "https://flagpedia.net/data/flags/w580/fi.png"));
        AllPlaces.add(new Place(
                "Stockholm, Sweden",
                "Stockholmers call their city 'beauty on water'. But despite the well-preserved historic core, Stockholm is no museum piece: it's modern, dynamic and ever-evolving.",
                "https://a.cdn-hotels.com/gdcs/production151/d1898/abc9d800-c31d-11e8-825c-0242ac110006.jpg",
                "https://flagpedia.net/data/flags/w580/se.png"));
        AllPlaces.add(new Place(
                "Reykjavik, Iceland",
                "The world’s most northerly capital combines colourful buildings, quirky, creative people, eye-popping design, wild nightlife and a capricious soul.",
                "https://www.roughguides.com/wp-content/uploads/2013/01/hallgrimskirkja-cathedral-city-reykjavik-iceland-shutterstock_613997816.jpg",
                "https://flagpedia.net/data/flags/w580/is.png"));
        AllPlaces.add(new Place(
                "Oslo, Norway",
                "Surrounded by mountains and sea, this compact, cultured and fun city has a palpable sense of reinvention.",
                "https://cdn-image.travelandleisure.com/sites/default/files/styles/1600x1000/public/1498156851/opera-house-oslo-norway-OSLO0617.jpg?itok=rcuoL2da",
                "https://flagpedia.net/data/flags/w580/no.png"));
        AllPlaces.add(new Place(
                "Copenhagen, Denmark",
                "Copenhagen is the epitome of Scandi cool. Modernist lamps light New Nordic tables, bridges buzz with cycling commuters and eye-candy locals dive into pristine waterways.",
                "https://lonelyplanetimages.imgix.net/mastheads/GettyImages-126140612_super%20.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/dk.png"));
        AllPlaces.add(new Place(
                "Amsterdam, Netherlands",
                "Golden Age canals lined by tilting gabled buildings are the backdrop for Amsterdam's treasure-packed museums, vintage-filled shops and hyper-creative drinking, dining and design scenes.",
                "http://cdn.cnn.com/cnnnext/dam/assets/190315131613-07-netherlands-happiest-coutries-2018.jpg",
                "https://flagpedia.net/data/flags/w580/nl.png"));
        AllPlaces.add(new Place(
                "Dublin, Ireland",
                "A small capital with a huge reputation, Dublin has a mix of heritage and hedonism that will not disappoint. All you have to do is show up.",
                "https://thesefootballtimes.co/wp-content/uploads/2015/10/Dublin-Ireland.jpg",
                "https://flagpedia.net/data/flags/w580/ie.png"));
        AllPlaces.add(new Place(
                "Brussels, Belgium",
                "Historic yet hip, bureaucratic yet bizarre, self-confident yet unshowy, Brussels is multicultural to its roots.",
                "https://media.timeout.com/images/105237721/image.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/Flag_of_Belgium.svg/2000px-Flag_of_Belgium.svg.png"));
        adapter.notifyDataSetChanged();
    }

    public void addSpringAutumnWinter()
    {
        AllPlaces.clear();
        AllPlaces.add(new Place(
                "Skopje, Macedonia",
                "Skopje has plenty of charm. Its Ottoman- and Byzantine-era sights are focused around the city's delightful Čaršija, bordered by the 15th-century Kameni Most (Stone Bridge) and Tvrdina Kale Fortress – Skopje's guardian since the 5th century.",
                "https://lonelyplanetimages.imgix.net/mastheads/83405857%20.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/mk.png"));
        AllPlaces.add(new Place(
                "Pristina, Kosovo",
                "Pristina is a fast-changing city that feels full of optimism and potential, even if its traffic-clogged streets and mismatched architectural styles don't make it an obviously attractive place.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/e228bf3be784ffff7a338ec8d9167d30-pristina.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/ks.png"));
        AllPlaces.add(new Place(
                "Tirana, Albania",
                "Lively, colourful Tirana is where this tiny nation's hopes and dreams coalesce into a vibrant whirl of traffic, brash consumerism and unfettered fun.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/25e80d94ce0c6dfee0634a2b8598feae-tirana.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/al.png"));
        AllPlaces.add(new Place(
                "Podgorica, Montenegro",
                "Given it’s undergone five name changes, passed through the hands of everyone from the Romans to the Turks to the Austro-Hungarians, and twice been wiped off the map entirely, it’s little wonder that Podgorica (Подгорица) seems permanently gripped by an identity crisis.",
                "https://lonelyplanetimages.imgix.net/mastheads/147031066_medium.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/me.png"));
        AllPlaces.add(new Place(
                "Sarajevo, Bosnia Herzegovina",
                "Ringed by mountains, Sarajevo is a singular city with an enticing East-meets-West vibe all of its own. It was once renowned as a religious melting pot, earning it the epithet 'the Jerusalem of Europe'.",
                "https://s23835.pcdn.co/wp-content/uploads/2018/07/Bosnia-Sarajevo-view-4.jpg",
                "https://flagpedia.net/data/flags/w580/ba.png"));
        AllPlaces.add(new Place(
                "Belgrade, Serbia",
                "Outspoken, adventurous, proud and audacious: Belgrade ('White City') is by no means a 'pretty' capital, but its gritty exuberance makes it one of Europe's most happening cities.",
                "https://media.architecturaldigest.com/photos/5d37554e47a77b000872bbf4/16:9/w_1280,c_limit/GettyImages-847708760.jpg",
                "https://flagpedia.net/data/flags/w580/rs.png"));
        AllPlaces.add(new Place(
                "Zagreb, Croatia",
                "Zagreb is made for strolling. Wander through the Upper Town's red-roof and cobblestone glory, peppered with church spires.",
                "https://brightcove04pmdo-a.akamaihd.net/5104226627001/5104226627001_5441113691001_5428833703001-vs.jpg?pubId=5104226627001&videoId=5428833703001",
                "https://flagpedia.net/data/flags/w580/hr.png"));
        AllPlaces.add(new Place(
                "Ljubljana, Slovenia",
                "Slovenia's capital and largest city is one of Europe's greenest and most liveable capitals; it was the European Commission's Green Capital of Europe in 2016.",
                "https://lonelyplanetimages.imgix.net/mastheads/GettyImages-494314513_high.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/si.png"));
        AllPlaces.add(new Place(
                "Sofia, Bulgaria",
                "Bulgaria's pleasingly laid-back capital is often overlooked by visitors heading to the coast or the ski resorts, but they're missing something special.",
                "https://happytowander.com/wp-content/uploads/Sofia-Bulgaria-Happy-to-Wander-0609.jpg",
                "https://flagpedia.net/data/flags/w580/bg.png"));
        AllPlaces.add(new Place(
                "Chisinau, Moldova",
                "Pretty much all roads in Moldova lead to its wine-and-food loving capital.",
                "https://d1z15fh6odiy9s.cloudfront.net/plonk/adfb.../adfba780ee49dd02aa788772e39b7b44497517c80233d3e1cbad1c138f694a02-large.jpg",
                "https://flagpedia.net/data/flags/w580/md.png"));
        AllPlaces.add(new Place(
                "Bucharest, Romania",
                "Romania’s capital sometimes gets a bad rap, but in fact it's dynamic, energetic and lots of fun. Many travellers give the city just a night or two before heading off to Transylvania, but that’s not enough time.",
                "https://www.romania-insider.com/sites/default/files/styles/article_large_image/public/2019-02/bucharest_universitatii_square_-_shutterstock.jpg",
                "https://flagpedia.net/data/flags/w580/ro.png"));
        AllPlaces.add(new Place(
                "Budapest, Hungary",
                "Budapest has something for everyone – from dramatic history and flamboyant architecture to healing thermal waters and a nightlife that is unrivalled in Eastern and Central Europe.",
                "https://brightcove04pmdo-a.akamaihd.net/5104226627001/5104226627001_5515999951001_5214933106001-vs.jpg?pubId=5104226627001&videoId=5214933106001",
                "https://flagpedia.net/data/flags/w580/hu.png"));
        AllPlaces.add(new Place(
                "Bern, Switzerland",
                "Wandering through the picture-postcard, Unesco World Heritage–listed Old Town, with its provincial, laid-back air, it's hard to believe that Bern (Berne in French) is the capital of Switzerland.",
                "https://d2mpqlmtgl1znu.cloudfront.net/AcuCustom/Sitename/DAM/017/Bern_Adobe.jpg",
                "https://flagpedia.net/data/flags/w580/ch.png"));
        AllPlaces.add(new Place(
                "Vienna, Austria",
                "Baroque streetscapes and imperial palaces set the stage for Vienna's artistic and musical masterpieces alongside its coffee-house culture and vibrant epicurean and design scenes.",
                "https://img.theculturetrip.com/x/smart/wp-content/uploads/2019/04/eur-austria-vienna.jpg",
                "https://flagpedia.net/data/flags/w580/at.png"));
        AllPlaces.add(new Place(
                "Bratislava, Slovakia",
                "Slovakia's capital since the country's independence in 1993, Bratislava is a mosaic of illustrious history: a medieval and Gothic old town, baroque palaces commissioned by Hungarian nobles, and the crowning castle, rebuilt to Renaissance finery.",
                "https://s27363.pcdn.co/wp-content/uploads/2018/08/Bratislava-Slovakia-1129x752.jpg.optimal.jpg",
                "https://flagpedia.net/data/flags/w580/sk.png"));
        AllPlaces.add(new Place(
                "Prague, Czech Republic",
                "Prague is the equal of Paris in terms of beauty. Its history goes back a millennium. And the beer? The best in Europe.",
                "https://cdn.thecrazytourist.com/wp-content/uploads/2018/06/ccimage-shutterstock_428804917.jpg",
                "https://flagpedia.net/data/flags/w580/cz.png"));
        AllPlaces.add(new Place(
                "Kiev, Ukraine",
                "Long before Ukraine and Russia existed, the city's inhabitants were already striding up and down the green hills, idling hot afternoons away on the Dnipro River and promenading along Khreshchatyk then a stream, now the main avenue.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/a6469f3993128cbcdde6e2b0a90aa1d3-kyiv.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/ua.png"));
        AllPlaces.add(new Place(
                "Warsaw, Poland",
                "A phoenix arisen from the ashes, Poland's capital impresses with its resilience, respect for history, contemporary style and sheer joie de vivre.",
                "https://www.roughguides.com/wp-content/uploads/2013/04/warsaw-old-town-royal-castle-poland-shutterstock_1171262353.jpg",
                "https://flagpedia.net/data/flags/w580/pl.png"));
        AllPlaces.add(new Place(
                "Minsk, Belarus",
                "Minsk will almost certainly surprise you. The capital of Belarus is, contrary to its dreary reputation, a progressive, modern and clean place.",
                "https://lonelyplanetimages.imgix.net/mastheads/513008517_large.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/by.png"));
        AllPlaces.add(new Place(
                "Vilnius, Lithuania",
                "There is a dreamy quality to Vilnius (vil-nyus), especially in the golden glow of a midsummer evening.",
                "https://content.r9cdn.net/rimg/dimg/09/c0/92c9d67d-city-7110-162f78f1fbc.jpg?crop=true&width=1000&height=600&xhint=2126&yhint=1263",
                "https://flagpedia.net/data/flags/w580/lt.png"));
        AllPlaces.add(new Place(
                "Riga, Latvia",
                "The Gothic spires that dominate Rīga's cityscape might suggest austerity, but it is the flamboyant art nouveau that forms the flesh and the spirit of this vibrant cosmopolitan city, the largest of all three Baltic capitals.",
                "https://www.roadaffair.com/wp-content/uploads/2019/03/house-blackheads-saint-peters-church-riga-latvia-shutterstock_1070326685-1024x683.jpg",
                "https://flagpedia.net/data/flags/w580/lv.png"));
        AllPlaces.add(new Place(
                "Tallinn, Estonia",
                "No longer the plaything of greater powers – Danish, Swedish, Polish, German and Soviet – Tallinn is now a proud European capital with an allure all of its own.",
                "https://www.nomadepicureans.com/wp-content/uploads/2019/05/Tallinn-Walking-Tour-Feature-Image.jpg",
                "https://flagpedia.net/data/flags/w580/ee.png"));
        AllPlaces.add(new Place(
                "Helsinki, Finland",
                "Entwined with the Baltic's bays, inlets and islands, Helsinki's boulevards and backstreets overflow with magnificent architecture, intriguing drinking and dining venues and groundbreaking design.",
                "http://www.hel.fi/wps/wcm/connect/5a20f5c2-6516-40e7-a239-ef6585fdfcfd/englkielisetpalvelutuutiskuva.jpg?MOD=AJPERES&CACHEID=ROOTWORKSPACE-5a20f5c2-6516-40e7-a239-ef6585fdfcfd-mz18k6W",
                "https://flagpedia.net/data/flags/w580/fi.png"));
        AllPlaces.add(new Place(
                "Stockholm, Sweden",
                "Stockholmers call their city 'beauty on water'. But despite the well-preserved historic core, Stockholm is no museum piece: it's modern, dynamic and ever-evolving.",
                "https://a.cdn-hotels.com/gdcs/production151/d1898/abc9d800-c31d-11e8-825c-0242ac110006.jpg",
                "https://flagpedia.net/data/flags/w580/se.png"));
        AllPlaces.add(new Place(
                "Reykjavik, Iceland",
                "The world’s most northerly capital combines colourful buildings, quirky, creative people, eye-popping design, wild nightlife and a capricious soul.",
                "https://www.roughguides.com/wp-content/uploads/2013/01/hallgrimskirkja-cathedral-city-reykjavik-iceland-shutterstock_613997816.jpg",
                "https://flagpedia.net/data/flags/w580/is.png"));
        AllPlaces.add(new Place(
                "Oslo, Norway",
                "Surrounded by mountains and sea, this compact, cultured and fun city has a palpable sense of reinvention.",
                "https://cdn-image.travelandleisure.com/sites/default/files/styles/1600x1000/public/1498156851/opera-house-oslo-norway-OSLO0617.jpg?itok=rcuoL2da",
                "https://flagpedia.net/data/flags/w580/no.png"));
        AllPlaces.add(new Place(
                "Copenhagen, Denmark",
                "Copenhagen is the epitome of Scandi cool. Modernist lamps light New Nordic tables, bridges buzz with cycling commuters and eye-candy locals dive into pristine waterways.",
                "https://lonelyplanetimages.imgix.net/mastheads/GettyImages-126140612_super%20.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/dk.png"));
        AllPlaces.add(new Place(
                "Amsterdam, Netherlands",
                "Golden Age canals lined by tilting gabled buildings are the backdrop for Amsterdam's treasure-packed museums, vintage-filled shops and hyper-creative drinking, dining and design scenes.",
                "http://cdn.cnn.com/cnnnext/dam/assets/190315131613-07-netherlands-happiest-coutries-2018.jpg",
                "https://flagpedia.net/data/flags/w580/nl.png"));
        AllPlaces.add(new Place(
                "Dublin, Ireland",
                "A small capital with a huge reputation, Dublin has a mix of heritage and hedonism that will not disappoint. All you have to do is show up.",
                "https://thesefootballtimes.co/wp-content/uploads/2015/10/Dublin-Ireland.jpg",
                "https://flagpedia.net/data/flags/w580/ie.png"));
        AllPlaces.add(new Place(
                "Brussels, Belgium",
                "Historic yet hip, bureaucratic yet bizarre, self-confident yet unshowy, Brussels is multicultural to its roots.",
                "https://media.timeout.com/images/105237721/image.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/Flag_of_Belgium.svg/2000px-Flag_of_Belgium.svg.png"));

        adapter.notifyDataSetChanged();
    }

    public void addSpringSummerWinter()
    {
        AllPlaces.clear();
        AllPlaces.add(new Place(
                "Skopje, Macedonia",
                "Skopje has plenty of charm. Its Ottoman- and Byzantine-era sights are focused around the city's delightful Čaršija, bordered by the 15th-century Kameni Most (Stone Bridge) and Tvrdina Kale Fortress – Skopje's guardian since the 5th century.",
                "https://lonelyplanetimages.imgix.net/mastheads/83405857%20.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/mk.png"));
        AllPlaces.add(new Place(
                "Pristina, Kosovo",
                "Pristina is a fast-changing city that feels full of optimism and potential, even if its traffic-clogged streets and mismatched architectural styles don't make it an obviously attractive place.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/e228bf3be784ffff7a338ec8d9167d30-pristina.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/ks.png"));
        AllPlaces.add(new Place(
                "Tirana, Albania",
                "Lively, colourful Tirana is where this tiny nation's hopes and dreams coalesce into a vibrant whirl of traffic, brash consumerism and unfettered fun.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/25e80d94ce0c6dfee0634a2b8598feae-tirana.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/al.png"));
        AllPlaces.add(new Place(
                "Podgorica, Montenegro",
                "Given it’s undergone five name changes, passed through the hands of everyone from the Romans to the Turks to the Austro-Hungarians, and twice been wiped off the map entirely, it’s little wonder that Podgorica (Подгорица) seems permanently gripped by an identity crisis.",
                "https://lonelyplanetimages.imgix.net/mastheads/147031066_medium.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/me.png"));
        AllPlaces.add(new Place(
                "Sarajevo, Bosnia Herzegovina",
                "Ringed by mountains, Sarajevo is a singular city with an enticing East-meets-West vibe all of its own. It was once renowned as a religious melting pot, earning it the epithet 'the Jerusalem of Europe'.",
                "https://s23835.pcdn.co/wp-content/uploads/2018/07/Bosnia-Sarajevo-view-4.jpg",
                "https://flagpedia.net/data/flags/w580/ba.png"));
        AllPlaces.add(new Place(
                "Belgrade, Serbia",
                "Outspoken, adventurous, proud and audacious: Belgrade ('White City') is by no means a 'pretty' capital, but its gritty exuberance makes it one of Europe's most happening cities.",
                "https://media.architecturaldigest.com/photos/5d37554e47a77b000872bbf4/16:9/w_1280,c_limit/GettyImages-847708760.jpg",
                "https://flagpedia.net/data/flags/w580/rs.png"));
        AllPlaces.add(new Place(
                "Zagreb, Croatia",
                "Zagreb is made for strolling. Wander through the Upper Town's red-roof and cobblestone glory, peppered with church spires.",
                "https://brightcove04pmdo-a.akamaihd.net/5104226627001/5104226627001_5441113691001_5428833703001-vs.jpg?pubId=5104226627001&videoId=5428833703001",
                "https://flagpedia.net/data/flags/w580/hr.png"));
        AllPlaces.add(new Place(
                "Ljubljana, Slovenia",
                "Slovenia's capital and largest city is one of Europe's greenest and most liveable capitals; it was the European Commission's Green Capital of Europe in 2016.",
                "https://lonelyplanetimages.imgix.net/mastheads/GettyImages-494314513_high.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/si.png"));
        AllPlaces.add(new Place(
                "Sofia, Bulgaria",
                "Bulgaria's pleasingly laid-back capital is often overlooked by visitors heading to the coast or the ski resorts, but they're missing something special.",
                "https://happytowander.com/wp-content/uploads/Sofia-Bulgaria-Happy-to-Wander-0609.jpg",
                "https://flagpedia.net/data/flags/w580/bg.png"));
        AllPlaces.add(new Place(
                "Istanbul, Turkey",
                "This magical meeting place of East and West has more top-drawer attractions than it has minarets (and that's a lot).",
                "http://blog.radissonblu.com/wp-content/uploads/2017/07/Blue-Mosque.jpg",
                "https://flagpedia.net/data/flags/w580/tr.png"));
        AllPlaces.add(new Place(
                "Rome, Italy",
                "A heady mix of haunting ruins, awe-inspiring art and vibrant street life, Italy’s hot-blooded capital is one of the world’s most romantic and charismatic cities.",
                "https://cdn.citywonders.com/media/17764/rome-city.jpg?anchor=center&mode=crop&width=1200&height=600",
                "https://flagpedia.net/data/flags/w580/it.png"));
        AllPlaces.add(new Place(
                "Berlin, Germany",
                "Berlin's combo of glamour and grit is bound to mesmerise all those keen to explore its vibrant culture, cutting-edge architecture, fabulous food, intense parties and tangible history.",
                "https://www.berlin-welcomecard.de/sites/default/files/styles/stage_desktop_20x/public/berlin-welcomecard_hero_2880__1.jpg?itok=fgbQwjEO&timestamp=1528123533",
                "https://flagpedia.net/data/flags/ultra/de.png"));
        AllPlaces.add(new Place(
                "Paris, France",
                "Paris' monument-lined boulevards, museums, classical bistros and boutiques are enhanced by a new wave of multimedia galleries, creative wine bars, design shops and tech start-ups.",
                "https://www.fodors.com/wp-content/uploads/2018/10/HERO_UltimateParis_Heroshutterstock_112137761.jpg",
                "https://flagpedia.net/data/flags/ultra/fr.png"));
        AllPlaces.add(new Place(
                "Lisbon, Portugal",
                "Seven cinematic hillsides overlooking the Rio Tejo cradle Lisbon's postcard-perfect panorama of cobbled alleyways, ancient ruins and white-domed cathedrals a captivating scene crafted over centuries.",
                "https://www.lisbonguru.com/wp-content/uploads/2015/11/viewpoints-lisbon.jpg",
                "https://flagpedia.net/data/flags/ultra/pt.png"));
        AllPlaces.add(new Place(
                "Madrid, Spain",
                "Madrid is a beguiling place with an energy that carries one simple message: this city really knows how to live.",
                "https://avisassets.abgemea.com/dam/jcr:afda6cf9-fc35-4c47-940a-baa594ab04ed/Grand_Via_Madrid_Credit_iStock_kasto80.jpg",
                "https://flagpedia.net/data/flags/ultra/es.png"));
        AllPlaces.add(new Place(
                "London, United Kingdom",
                "Immersed in history, London's rich seams of eye-opening antiquity are everywhere.",
                "http://qarva.com/wp-content/gallery/ott-2018/london-background-14.jpeg",
                "https://flagpedia.net/data/flags/ultra/gb.png"));
        AllPlaces.add(new Place(
                "Santorini, Greece",
                "Santorini is the supermodel of the Greek islands, a head-turner whose face is instantly recognisable around the world: multicoloured cliffs soar out of a sea-drowned caldera, topped by drifts of whitewashed buildings.",
                "https://dak95nwic4sny.cloudfront.net/73/santorini-44228168-1550653710-ImageGalleryLightboxLarge.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Flag_of_Greece.svg/2000px-Flag_of_Greece.svg.png"));
        AllPlaces.add(new Place(
                "Helsinki, Finland",
                "Entwined with the Baltic's bays, inlets and islands, Helsinki's boulevards and backstreets overflow with magnificent architecture, intriguing drinking and dining venues and groundbreaking design.",
                "http://www.hel.fi/wps/wcm/connect/5a20f5c2-6516-40e7-a239-ef6585fdfcfd/englkielisetpalvelutuutiskuva.jpg?MOD=AJPERES&CACHEID=ROOTWORKSPACE-5a20f5c2-6516-40e7-a239-ef6585fdfcfd-mz18k6W",
                "https://flagpedia.net/data/flags/w580/fi.png"));
        AllPlaces.add(new Place(
                "Stockholm, Sweden",
                "Stockholmers call their city 'beauty on water'. But despite the well-preserved historic core, Stockholm is no museum piece: it's modern, dynamic and ever-evolving.",
                "https://a.cdn-hotels.com/gdcs/production151/d1898/abc9d800-c31d-11e8-825c-0242ac110006.jpg",
                "https://flagpedia.net/data/flags/w580/se.png"));
        AllPlaces.add(new Place(
                "Reykjavik, Iceland",
                "The world’s most northerly capital combines colourful buildings, quirky, creative people, eye-popping design, wild nightlife and a capricious soul.",
                "https://www.roughguides.com/wp-content/uploads/2013/01/hallgrimskirkja-cathedral-city-reykjavik-iceland-shutterstock_613997816.jpg",
                "https://flagpedia.net/data/flags/w580/is.png"));
        AllPlaces.add(new Place(
                "Oslo, Norway",
                "Surrounded by mountains and sea, this compact, cultured and fun city has a palpable sense of reinvention.",
                "https://cdn-image.travelandleisure.com/sites/default/files/styles/1600x1000/public/1498156851/opera-house-oslo-norway-OSLO0617.jpg?itok=rcuoL2da",
                "https://flagpedia.net/data/flags/w580/no.png"));
        AllPlaces.add(new Place(
                "Copenhagen, Denmark",
                "Copenhagen is the epitome of Scandi cool. Modernist lamps light New Nordic tables, bridges buzz with cycling commuters and eye-candy locals dive into pristine waterways.",
                "https://lonelyplanetimages.imgix.net/mastheads/GettyImages-126140612_super%20.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/dk.png"));
        AllPlaces.add(new Place(
                "Amsterdam, Netherlands",
                "Golden Age canals lined by tilting gabled buildings are the backdrop for Amsterdam's treasure-packed museums, vintage-filled shops and hyper-creative drinking, dining and design scenes.",
                "http://cdn.cnn.com/cnnnext/dam/assets/190315131613-07-netherlands-happiest-coutries-2018.jpg",
                "https://flagpedia.net/data/flags/w580/nl.png"));
        AllPlaces.add(new Place(
                "Dublin, Ireland",
                "A small capital with a huge reputation, Dublin has a mix of heritage and hedonism that will not disappoint. All you have to do is show up.",
                "https://thesefootballtimes.co/wp-content/uploads/2015/10/Dublin-Ireland.jpg",
                "https://flagpedia.net/data/flags/w580/ie.png"));
        AllPlaces.add(new Place(
                "Brussels, Belgium",
                "Historic yet hip, bureaucratic yet bizarre, self-confident yet unshowy, Brussels is multicultural to its roots.",
                "https://media.timeout.com/images/105237721/image.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/Flag_of_Belgium.svg/2000px-Flag_of_Belgium.svg.png"));
        adapter.notifyDataSetChanged();
    }

    public void addSpringSummer()
    {
        AllPlaces.clear();
        AllPlaces.add(new Place(
                "Skopje, Macedonia",
                "Skopje has plenty of charm. Its Ottoman- and Byzantine-era sights are focused around the city's delightful Čaršija, bordered by the 15th-century Kameni Most (Stone Bridge) and Tvrdina Kale Fortress – Skopje's guardian since the 5th century.",
                "https://lonelyplanetimages.imgix.net/mastheads/83405857%20.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/mk.png"));
        AllPlaces.add(new Place(
                "Pristina, Kosovo",
                "Pristina is a fast-changing city that feels full of optimism and potential, even if its traffic-clogged streets and mismatched architectural styles don't make it an obviously attractive place.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/e228bf3be784ffff7a338ec8d9167d30-pristina.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/ks.png"));
        AllPlaces.add(new Place(
                "Tirana, Albania",
                "Lively, colourful Tirana is where this tiny nation's hopes and dreams coalesce into a vibrant whirl of traffic, brash consumerism and unfettered fun.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/25e80d94ce0c6dfee0634a2b8598feae-tirana.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/al.png"));
        AllPlaces.add(new Place(
                "Podgorica, Montenegro",
                "Given it’s undergone five name changes, passed through the hands of everyone from the Romans to the Turks to the Austro-Hungarians, and twice been wiped off the map entirely, it’s little wonder that Podgorica (Подгорица) seems permanently gripped by an identity crisis.",
                "https://lonelyplanetimages.imgix.net/mastheads/147031066_medium.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/me.png"));
        AllPlaces.add(new Place(
                "Sarajevo, Bosnia Herzegovina",
                "Ringed by mountains, Sarajevo is a singular city with an enticing East-meets-West vibe all of its own. It was once renowned as a religious melting pot, earning it the epithet 'the Jerusalem of Europe'.",
                "https://s23835.pcdn.co/wp-content/uploads/2018/07/Bosnia-Sarajevo-view-4.jpg",
                "https://flagpedia.net/data/flags/w580/ba.png"));
        AllPlaces.add(new Place(
                "Belgrade, Serbia",
                "Outspoken, adventurous, proud and audacious: Belgrade ('White City') is by no means a 'pretty' capital, but its gritty exuberance makes it one of Europe's most happening cities.",
                "https://media.architecturaldigest.com/photos/5d37554e47a77b000872bbf4/16:9/w_1280,c_limit/GettyImages-847708760.jpg",
                "https://flagpedia.net/data/flags/w580/rs.png"));
        AllPlaces.add(new Place(
                "Zagreb, Croatia",
                "Zagreb is made for strolling. Wander through the Upper Town's red-roof and cobblestone glory, peppered with church spires.",
                "https://brightcove04pmdo-a.akamaihd.net/5104226627001/5104226627001_5441113691001_5428833703001-vs.jpg?pubId=5104226627001&videoId=5428833703001",
                "https://flagpedia.net/data/flags/w580/hr.png"));
        AllPlaces.add(new Place(
                "Ljubljana, Slovenia",
                "Slovenia's capital and largest city is one of Europe's greenest and most liveable capitals; it was the European Commission's Green Capital of Europe in 2016.",
                "https://lonelyplanetimages.imgix.net/mastheads/GettyImages-494314513_high.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/si.png"));
        AllPlaces.add(new Place(
                "Sofia, Bulgaria",
                "Bulgaria's pleasingly laid-back capital is often overlooked by visitors heading to the coast or the ski resorts, but they're missing something special.",
                "https://happytowander.com/wp-content/uploads/Sofia-Bulgaria-Happy-to-Wander-0609.jpg",
                "https://flagpedia.net/data/flags/w580/bg.png"));
        AllPlaces.add(new Place(
                "Istanbul, Turkey",
                "This magical meeting place of East and West has more top-drawer attractions than it has minarets (and that's a lot).",
                "http://blog.radissonblu.com/wp-content/uploads/2017/07/Blue-Mosque.jpg",
                "https://flagpedia.net/data/flags/w580/tr.png"));
        AllPlaces.add(new Place(
                "Rome, Italy",
                "A heady mix of haunting ruins, awe-inspiring art and vibrant street life, Italy’s hot-blooded capital is one of the world’s most romantic and charismatic cities.",
                "https://cdn.citywonders.com/media/17764/rome-city.jpg?anchor=center&mode=crop&width=1200&height=600",
                "https://flagpedia.net/data/flags/w580/it.png"));
        AllPlaces.add(new Place(
                "Berlin, Germany",
                "Berlin's combo of glamour and grit is bound to mesmerise all those keen to explore its vibrant culture, cutting-edge architecture, fabulous food, intense parties and tangible history.",
                "https://www.berlin-welcomecard.de/sites/default/files/styles/stage_desktop_20x/public/berlin-welcomecard_hero_2880__1.jpg?itok=fgbQwjEO&timestamp=1528123533",
                "https://flagpedia.net/data/flags/ultra/de.png"));
        AllPlaces.add(new Place(
                "Paris, France",
                "Paris' monument-lined boulevards, museums, classical bistros and boutiques are enhanced by a new wave of multimedia galleries, creative wine bars, design shops and tech start-ups.",
                "https://www.fodors.com/wp-content/uploads/2018/10/HERO_UltimateParis_Heroshutterstock_112137761.jpg",
                "https://flagpedia.net/data/flags/ultra/fr.png"));
        AllPlaces.add(new Place(
                "Lisbon, Portugal",
                "Seven cinematic hillsides overlooking the Rio Tejo cradle Lisbon's postcard-perfect panorama of cobbled alleyways, ancient ruins and white-domed cathedrals a captivating scene crafted over centuries.",
                "https://www.lisbonguru.com/wp-content/uploads/2015/11/viewpoints-lisbon.jpg",
                "https://flagpedia.net/data/flags/ultra/pt.png"));
        AllPlaces.add(new Place(
                "Madrid, Spain",
                "Madrid is a beguiling place with an energy that carries one simple message: this city really knows how to live.",
                "https://avisassets.abgemea.com/dam/jcr:afda6cf9-fc35-4c47-940a-baa594ab04ed/Grand_Via_Madrid_Credit_iStock_kasto80.jpg",
                "https://flagpedia.net/data/flags/ultra/es.png"));
        AllPlaces.add(new Place(
                "London, United Kingdom",
                "Immersed in history, London's rich seams of eye-opening antiquity are everywhere.",
                "http://qarva.com/wp-content/gallery/ott-2018/london-background-14.jpeg",
                "https://flagpedia.net/data/flags/ultra/gb.png"));
        AllPlaces.add(new Place(
                "Santorini, Greece",
                "Santorini is the supermodel of the Greek islands, a head-turner whose face is instantly recognisable around the world: multicoloured cliffs soar out of a sea-drowned caldera, topped by drifts of whitewashed buildings.",
                "https://dak95nwic4sny.cloudfront.net/73/santorini-44228168-1550653710-ImageGalleryLightboxLarge.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Flag_of_Greece.svg/2000px-Flag_of_Greece.svg.png"));
        adapter.notifyDataSetChanged();
    }

    public void addSpringAutumn()
    {
        AllPlaces.clear();
        AllPlaces.add(new Place(
                "Skopje, Macedonia",
                "Skopje has plenty of charm. Its Ottoman- and Byzantine-era sights are focused around the city's delightful Čaršija, bordered by the 15th-century Kameni Most (Stone Bridge) and Tvrdina Kale Fortress – Skopje's guardian since the 5th century.",
                "https://lonelyplanetimages.imgix.net/mastheads/83405857%20.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/mk.png"));
        AllPlaces.add(new Place(
                "Pristina, Kosovo",
                "Pristina is a fast-changing city that feels full of optimism and potential, even if its traffic-clogged streets and mismatched architectural styles don't make it an obviously attractive place.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/e228bf3be784ffff7a338ec8d9167d30-pristina.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/ks.png"));
        AllPlaces.add(new Place(
                "Tirana, Albania",
                "Lively, colourful Tirana is where this tiny nation's hopes and dreams coalesce into a vibrant whirl of traffic, brash consumerism and unfettered fun.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/25e80d94ce0c6dfee0634a2b8598feae-tirana.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/al.png"));
        AllPlaces.add(new Place(
                "Podgorica, Montenegro",
                "Given it’s undergone five name changes, passed through the hands of everyone from the Romans to the Turks to the Austro-Hungarians, and twice been wiped off the map entirely, it’s little wonder that Podgorica (Подгорица) seems permanently gripped by an identity crisis.",
                "https://lonelyplanetimages.imgix.net/mastheads/147031066_medium.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/me.png"));
        AllPlaces.add(new Place(
                "Sarajevo, Bosnia Herzegovina",
                "Ringed by mountains, Sarajevo is a singular city with an enticing East-meets-West vibe all of its own. It was once renowned as a religious melting pot, earning it the epithet 'the Jerusalem of Europe'.",
                "https://s23835.pcdn.co/wp-content/uploads/2018/07/Bosnia-Sarajevo-view-4.jpg",
                "https://flagpedia.net/data/flags/w580/ba.png"));
        AllPlaces.add(new Place(
                "Belgrade, Serbia",
                "Outspoken, adventurous, proud and audacious: Belgrade ('White City') is by no means a 'pretty' capital, but its gritty exuberance makes it one of Europe's most happening cities.",
                "https://media.architecturaldigest.com/photos/5d37554e47a77b000872bbf4/16:9/w_1280,c_limit/GettyImages-847708760.jpg",
                "https://flagpedia.net/data/flags/w580/rs.png"));
        AllPlaces.add(new Place(
                "Zagreb, Croatia",
                "Zagreb is made for strolling. Wander through the Upper Town's red-roof and cobblestone glory, peppered with church spires.",
                "https://brightcove04pmdo-a.akamaihd.net/5104226627001/5104226627001_5441113691001_5428833703001-vs.jpg?pubId=5104226627001&videoId=5428833703001",
                "https://flagpedia.net/data/flags/w580/hr.png"));
        AllPlaces.add(new Place(
                "Ljubljana, Slovenia",
                "Slovenia's capital and largest city is one of Europe's greenest and most liveable capitals; it was the European Commission's Green Capital of Europe in 2016.",
                "https://lonelyplanetimages.imgix.net/mastheads/GettyImages-494314513_high.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/si.png"));
        AllPlaces.add(new Place(
                "Sofia, Bulgaria",
                "Bulgaria's pleasingly laid-back capital is often overlooked by visitors heading to the coast or the ski resorts, but they're missing something special.",
                "https://happytowander.com/wp-content/uploads/Sofia-Bulgaria-Happy-to-Wander-0609.jpg",
                "https://flagpedia.net/data/flags/w580/bg.png"));
        AllPlaces.add(new Place(
                "Chisinau, Moldova",
                "Pretty much all roads in Moldova lead to its wine-and-food loving capital.",
                "https://d1z15fh6odiy9s.cloudfront.net/plonk/adfb.../adfba780ee49dd02aa788772e39b7b44497517c80233d3e1cbad1c138f694a02-large.jpg",
                "https://flagpedia.net/data/flags/w580/md.png"));
        AllPlaces.add(new Place(
                "Bucharest, Romania",
                "Romania’s capital sometimes gets a bad rap, but in fact it's dynamic, energetic and lots of fun. Many travellers give the city just a night or two before heading off to Transylvania, but that’s not enough time.",
                "https://www.romania-insider.com/sites/default/files/styles/article_large_image/public/2019-02/bucharest_universitatii_square_-_shutterstock.jpg",
                "https://flagpedia.net/data/flags/w580/ro.png"));
        AllPlaces.add(new Place(
                "Budapest, Hungary",
                "Budapest has something for everyone – from dramatic history and flamboyant architecture to healing thermal waters and a nightlife that is unrivalled in Eastern and Central Europe.",
                "https://brightcove04pmdo-a.akamaihd.net/5104226627001/5104226627001_5515999951001_5214933106001-vs.jpg?pubId=5104226627001&videoId=5214933106001",
                "https://flagpedia.net/data/flags/w580/hu.png"));
        AllPlaces.add(new Place(
                "Bern, Switzerland",
                "Wandering through the picture-postcard, Unesco World Heritage–listed Old Town, with its provincial, laid-back air, it's hard to believe that Bern (Berne in French) is the capital of Switzerland.",
                "https://d2mpqlmtgl1znu.cloudfront.net/AcuCustom/Sitename/DAM/017/Bern_Adobe.jpg",
                "https://flagpedia.net/data/flags/w580/ch.png"));
        AllPlaces.add(new Place(
                "Vienna, Austria",
                "Baroque streetscapes and imperial palaces set the stage for Vienna's artistic and musical masterpieces alongside its coffee-house culture and vibrant epicurean and design scenes.",
                "https://img.theculturetrip.com/x/smart/wp-content/uploads/2019/04/eur-austria-vienna.jpg",
                "https://flagpedia.net/data/flags/w580/at.png"));
        AllPlaces.add(new Place(
                "Bratislava, Slovakia",
                "Slovakia's capital since the country's independence in 1993, Bratislava is a mosaic of illustrious history: a medieval and Gothic old town, baroque palaces commissioned by Hungarian nobles, and the crowning castle, rebuilt to Renaissance finery.",
                "https://s27363.pcdn.co/wp-content/uploads/2018/08/Bratislava-Slovakia-1129x752.jpg.optimal.jpg",
                "https://flagpedia.net/data/flags/w580/sk.png"));
        AllPlaces.add(new Place(
                "Prague, Czech Republic",
                "Prague is the equal of Paris in terms of beauty. Its history goes back a millennium. And the beer? The best in Europe.",
                "https://cdn.thecrazytourist.com/wp-content/uploads/2018/06/ccimage-shutterstock_428804917.jpg",
                "https://flagpedia.net/data/flags/w580/cz.png"));
        AllPlaces.add(new Place(
                "Kiev, Ukraine",
                "Long before Ukraine and Russia existed, the city's inhabitants were already striding up and down the green hills, idling hot afternoons away on the Dnipro River and promenading along Khreshchatyk then a stream, now the main avenue.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/a6469f3993128cbcdde6e2b0a90aa1d3-kyiv.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/ua.png"));
        AllPlaces.add(new Place(
                "Warsaw, Poland",
                "A phoenix arisen from the ashes, Poland's capital impresses with its resilience, respect for history, contemporary style and sheer joie de vivre.",
                "https://www.roughguides.com/wp-content/uploads/2013/04/warsaw-old-town-royal-castle-poland-shutterstock_1171262353.jpg",
                "https://flagpedia.net/data/flags/w580/pl.png"));
        AllPlaces.add(new Place(
                "Minsk, Belarus",
                "Minsk will almost certainly surprise you. The capital of Belarus is, contrary to its dreary reputation, a progressive, modern and clean place.",
                "https://lonelyplanetimages.imgix.net/mastheads/513008517_large.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/by.png"));
        AllPlaces.add(new Place(
                "Vilnius, Lithuania",
                "There is a dreamy quality to Vilnius (vil-nyus), especially in the golden glow of a midsummer evening.",
                "https://content.r9cdn.net/rimg/dimg/09/c0/92c9d67d-city-7110-162f78f1fbc.jpg?crop=true&width=1000&height=600&xhint=2126&yhint=1263",
                "https://flagpedia.net/data/flags/w580/lt.png"));
        AllPlaces.add(new Place(
                "Riga, Latvia",
                "The Gothic spires that dominate Rīga's cityscape might suggest austerity, but it is the flamboyant art nouveau that forms the flesh and the spirit of this vibrant cosmopolitan city, the largest of all three Baltic capitals.",
                "https://www.roadaffair.com/wp-content/uploads/2019/03/house-blackheads-saint-peters-church-riga-latvia-shutterstock_1070326685-1024x683.jpg",
                "https://flagpedia.net/data/flags/w580/lv.png"));
        AllPlaces.add(new Place(
                "Tallinn, Estonia",
                "No longer the plaything of greater powers – Danish, Swedish, Polish, German and Soviet – Tallinn is now a proud European capital with an allure all of its own.",
                "https://www.nomadepicureans.com/wp-content/uploads/2019/05/Tallinn-Walking-Tour-Feature-Image.jpg",
                "https://flagpedia.net/data/flags/w580/ee.png"));

        adapter.notifyDataSetChanged();
    }

    public void addSpringWinter()
    {
        AllPlaces.clear();
        AllPlaces.add(new Place(
                "Skopje, Macedonia",
                "Skopje has plenty of charm. Its Ottoman- and Byzantine-era sights are focused around the city's delightful Čaršija, bordered by the 15th-century Kameni Most (Stone Bridge) and Tvrdina Kale Fortress – Skopje's guardian since the 5th century.",
                "https://lonelyplanetimages.imgix.net/mastheads/83405857%20.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/mk.png"));
        AllPlaces.add(new Place(
                "Pristina, Kosovo",
                "Pristina is a fast-changing city that feels full of optimism and potential, even if its traffic-clogged streets and mismatched architectural styles don't make it an obviously attractive place.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/e228bf3be784ffff7a338ec8d9167d30-pristina.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/ks.png"));
        AllPlaces.add(new Place(
                "Tirana, Albania",
                "Lively, colourful Tirana is where this tiny nation's hopes and dreams coalesce into a vibrant whirl of traffic, brash consumerism and unfettered fun.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/25e80d94ce0c6dfee0634a2b8598feae-tirana.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/al.png"));
        AllPlaces.add(new Place(
                "Podgorica, Montenegro",
                "Given it’s undergone five name changes, passed through the hands of everyone from the Romans to the Turks to the Austro-Hungarians, and twice been wiped off the map entirely, it’s little wonder that Podgorica (Подгорица) seems permanently gripped by an identity crisis.",
                "https://lonelyplanetimages.imgix.net/mastheads/147031066_medium.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/me.png"));
        AllPlaces.add(new Place(
                "Sarajevo, Bosnia Herzegovina",
                "Ringed by mountains, Sarajevo is a singular city with an enticing East-meets-West vibe all of its own. It was once renowned as a religious melting pot, earning it the epithet 'the Jerusalem of Europe'.",
                "https://s23835.pcdn.co/wp-content/uploads/2018/07/Bosnia-Sarajevo-view-4.jpg",
                "https://flagpedia.net/data/flags/w580/ba.png"));
        AllPlaces.add(new Place(
                "Belgrade, Serbia",
                "Outspoken, adventurous, proud and audacious: Belgrade ('White City') is by no means a 'pretty' capital, but its gritty exuberance makes it one of Europe's most happening cities.",
                "https://media.architecturaldigest.com/photos/5d37554e47a77b000872bbf4/16:9/w_1280,c_limit/GettyImages-847708760.jpg",
                "https://flagpedia.net/data/flags/w580/rs.png"));
        AllPlaces.add(new Place(
                "Zagreb, Croatia",
                "Zagreb is made for strolling. Wander through the Upper Town's red-roof and cobblestone glory, peppered with church spires.",
                "https://brightcove04pmdo-a.akamaihd.net/5104226627001/5104226627001_5441113691001_5428833703001-vs.jpg?pubId=5104226627001&videoId=5428833703001",
                "https://flagpedia.net/data/flags/w580/hr.png"));
        AllPlaces.add(new Place(
                "Ljubljana, Slovenia",
                "Slovenia's capital and largest city is one of Europe's greenest and most liveable capitals; it was the European Commission's Green Capital of Europe in 2016.",
                "https://lonelyplanetimages.imgix.net/mastheads/GettyImages-494314513_high.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/si.png"));
        AllPlaces.add(new Place(
                "Sofia, Bulgaria",
                "Bulgaria's pleasingly laid-back capital is often overlooked by visitors heading to the coast or the ski resorts, but they're missing something special.",
                "https://happytowander.com/wp-content/uploads/Sofia-Bulgaria-Happy-to-Wander-0609.jpg",
                "https://flagpedia.net/data/flags/w580/bg.png"));
        AllPlaces.add(new Place(
                "Helsinki, Finland",
                "Entwined with the Baltic's bays, inlets and islands, Helsinki's boulevards and backstreets overflow with magnificent architecture, intriguing drinking and dining venues and groundbreaking design.",
                "http://www.hel.fi/wps/wcm/connect/5a20f5c2-6516-40e7-a239-ef6585fdfcfd/englkielisetpalvelutuutiskuva.jpg?MOD=AJPERES&CACHEID=ROOTWORKSPACE-5a20f5c2-6516-40e7-a239-ef6585fdfcfd-mz18k6W",
                "https://flagpedia.net/data/flags/w580/fi.png"));
        AllPlaces.add(new Place(
                "Stockholm, Sweden",
                "Stockholmers call their city 'beauty on water'. But despite the well-preserved historic core, Stockholm is no museum piece: it's modern, dynamic and ever-evolving.",
                "https://a.cdn-hotels.com/gdcs/production151/d1898/abc9d800-c31d-11e8-825c-0242ac110006.jpg",
                "https://flagpedia.net/data/flags/w580/se.png"));
        AllPlaces.add(new Place(
                "Reykjavik, Iceland",
                "The world’s most northerly capital combines colourful buildings, quirky, creative people, eye-popping design, wild nightlife and a capricious soul.",
                "https://www.roughguides.com/wp-content/uploads/2013/01/hallgrimskirkja-cathedral-city-reykjavik-iceland-shutterstock_613997816.jpg",
                "https://flagpedia.net/data/flags/w580/is.png"));
        AllPlaces.add(new Place(
                "Oslo, Norway",
                "Surrounded by mountains and sea, this compact, cultured and fun city has a palpable sense of reinvention.",
                "https://cdn-image.travelandleisure.com/sites/default/files/styles/1600x1000/public/1498156851/opera-house-oslo-norway-OSLO0617.jpg?itok=rcuoL2da",
                "https://flagpedia.net/data/flags/w580/no.png"));
        AllPlaces.add(new Place(
                "Copenhagen, Denmark",
                "Copenhagen is the epitome of Scandi cool. Modernist lamps light New Nordic tables, bridges buzz with cycling commuters and eye-candy locals dive into pristine waterways.",
                "https://lonelyplanetimages.imgix.net/mastheads/GettyImages-126140612_super%20.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/dk.png"));
        AllPlaces.add(new Place(
                "Amsterdam, Netherlands",
                "Golden Age canals lined by tilting gabled buildings are the backdrop for Amsterdam's treasure-packed museums, vintage-filled shops and hyper-creative drinking, dining and design scenes.",
                "http://cdn.cnn.com/cnnnext/dam/assets/190315131613-07-netherlands-happiest-coutries-2018.jpg",
                "https://flagpedia.net/data/flags/w580/nl.png"));
        AllPlaces.add(new Place(
                "Dublin, Ireland",
                "A small capital with a huge reputation, Dublin has a mix of heritage and hedonism that will not disappoint. All you have to do is show up.",
                "https://thesefootballtimes.co/wp-content/uploads/2015/10/Dublin-Ireland.jpg",
                "https://flagpedia.net/data/flags/w580/ie.png"));
        AllPlaces.add(new Place(
                "Brussels, Belgium",
                "Historic yet hip, bureaucratic yet bizarre, self-confident yet unshowy, Brussels is multicultural to its roots.",
                "https://media.timeout.com/images/105237721/image.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/Flag_of_Belgium.svg/2000px-Flag_of_Belgium.svg.png"));

        adapter.notifyDataSetChanged();
    }

    public void  addSummerAutumn()
    {
        AllPlaces.clear();
        AllPlaces.add(new Place(
                "Istanbul, Turkey",
                "This magical meeting place of East and West has more top-drawer attractions than it has minarets (and that's a lot).",
                "http://blog.radissonblu.com/wp-content/uploads/2017/07/Blue-Mosque.jpg",
                "https://flagpedia.net/data/flags/w580/tr.png"));
        AllPlaces.add(new Place(
                "Rome, Italy",
                "A heady mix of haunting ruins, awe-inspiring art and vibrant street life, Italy’s hot-blooded capital is one of the world’s most romantic and charismatic cities.",
                "https://cdn.citywonders.com/media/17764/rome-city.jpg?anchor=center&mode=crop&width=1200&height=600",
                "https://flagpedia.net/data/flags/w580/it.png"));
        AllPlaces.add(new Place(
                "Berlin, Germany",
                "Berlin's combo of glamour and grit is bound to mesmerise all those keen to explore its vibrant culture, cutting-edge architecture, fabulous food, intense parties and tangible history.",
                "https://www.berlin-welcomecard.de/sites/default/files/styles/stage_desktop_20x/public/berlin-welcomecard_hero_2880__1.jpg?itok=fgbQwjEO&timestamp=1528123533",
                "https://flagpedia.net/data/flags/ultra/de.png"));
        AllPlaces.add(new Place(
                "Paris, France",
                "Paris' monument-lined boulevards, museums, classical bistros and boutiques are enhanced by a new wave of multimedia galleries, creative wine bars, design shops and tech start-ups.",
                "https://www.fodors.com/wp-content/uploads/2018/10/HERO_UltimateParis_Heroshutterstock_112137761.jpg",
                "https://flagpedia.net/data/flags/ultra/fr.png"));
        AllPlaces.add(new Place(
                "Lisbon, Portugal",
                "Seven cinematic hillsides overlooking the Rio Tejo cradle Lisbon's postcard-perfect panorama of cobbled alleyways, ancient ruins and white-domed cathedrals a captivating scene crafted over centuries.",
                "https://www.lisbonguru.com/wp-content/uploads/2015/11/viewpoints-lisbon.jpg",
                "https://flagpedia.net/data/flags/ultra/pt.png"));
        AllPlaces.add(new Place(
                "Madrid, Spain",
                "Madrid is a beguiling place with an energy that carries one simple message: this city really knows how to live.",
                "https://avisassets.abgemea.com/dam/jcr:afda6cf9-fc35-4c47-940a-baa594ab04ed/Grand_Via_Madrid_Credit_iStock_kasto80.jpg",
                "https://flagpedia.net/data/flags/ultra/es.png"));
        AllPlaces.add(new Place(
                "London, United Kingdom",
                "Immersed in history, London's rich seams of eye-opening antiquity are everywhere.",
                "http://qarva.com/wp-content/gallery/ott-2018/london-background-14.jpeg",
                "https://flagpedia.net/data/flags/ultra/gb.png"));
        AllPlaces.add(new Place(
                "Santorini, Greece",
                "Santorini is the supermodel of the Greek islands, a head-turner whose face is instantly recognisable around the world: multicoloured cliffs soar out of a sea-drowned caldera, topped by drifts of whitewashed buildings.",
                "https://dak95nwic4sny.cloudfront.net/73/santorini-44228168-1550653710-ImageGalleryLightboxLarge.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Flag_of_Greece.svg/2000px-Flag_of_Greece.svg.png"));
        AllPlaces.add(new Place(
                "Chisinau, Moldova",
                "Pretty much all roads in Moldova lead to its wine-and-food loving capital.",
                "https://d1z15fh6odiy9s.cloudfront.net/plonk/adfb.../adfba780ee49dd02aa788772e39b7b44497517c80233d3e1cbad1c138f694a02-large.jpg",
                "https://flagpedia.net/data/flags/w580/md.png"));
        AllPlaces.add(new Place(
                "Bucharest, Romania",
                "Romania’s capital sometimes gets a bad rap, but in fact it's dynamic, energetic and lots of fun. Many travellers give the city just a night or two before heading off to Transylvania, but that’s not enough time.",
                "https://www.romania-insider.com/sites/default/files/styles/article_large_image/public/2019-02/bucharest_universitatii_square_-_shutterstock.jpg",
                "https://flagpedia.net/data/flags/w580/ro.png"));
        AllPlaces.add(new Place(
                "Budapest, Hungary",
                "Budapest has something for everyone – from dramatic history and flamboyant architecture to healing thermal waters and a nightlife that is unrivalled in Eastern and Central Europe.",
                "https://brightcove04pmdo-a.akamaihd.net/5104226627001/5104226627001_5515999951001_5214933106001-vs.jpg?pubId=5104226627001&videoId=5214933106001",
                "https://flagpedia.net/data/flags/w580/hu.png"));
        AllPlaces.add(new Place(
                "Bern, Switzerland",
                "Wandering through the picture-postcard, Unesco World Heritage–listed Old Town, with its provincial, laid-back air, it's hard to believe that Bern (Berne in French) is the capital of Switzerland.",
                "https://d2mpqlmtgl1znu.cloudfront.net/AcuCustom/Sitename/DAM/017/Bern_Adobe.jpg",
                "https://flagpedia.net/data/flags/w580/ch.png"));
        AllPlaces.add(new Place(
                "Vienna, Austria",
                "Baroque streetscapes and imperial palaces set the stage for Vienna's artistic and musical masterpieces alongside its coffee-house culture and vibrant epicurean and design scenes.",
                "https://img.theculturetrip.com/x/smart/wp-content/uploads/2019/04/eur-austria-vienna.jpg",
                "https://flagpedia.net/data/flags/w580/at.png"));
        AllPlaces.add(new Place(
                "Bratislava, Slovakia",
                "Slovakia's capital since the country's independence in 1993, Bratislava is a mosaic of illustrious history: a medieval and Gothic old town, baroque palaces commissioned by Hungarian nobles, and the crowning castle, rebuilt to Renaissance finery.",
                "https://s27363.pcdn.co/wp-content/uploads/2018/08/Bratislava-Slovakia-1129x752.jpg.optimal.jpg",
                "https://flagpedia.net/data/flags/w580/sk.png"));
        AllPlaces.add(new Place(
                "Prague, Czech Republic",
                "Prague is the equal of Paris in terms of beauty. Its history goes back a millennium. And the beer? The best in Europe.",
                "https://cdn.thecrazytourist.com/wp-content/uploads/2018/06/ccimage-shutterstock_428804917.jpg",
                "https://flagpedia.net/data/flags/w580/cz.png"));
        AllPlaces.add(new Place(
                "Kiev, Ukraine",
                "Long before Ukraine and Russia existed, the city's inhabitants were already striding up and down the green hills, idling hot afternoons away on the Dnipro River and promenading along Khreshchatyk then a stream, now the main avenue.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/a6469f3993128cbcdde6e2b0a90aa1d3-kyiv.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/ua.png"));
        AllPlaces.add(new Place(
                "Warsaw, Poland",
                "A phoenix arisen from the ashes, Poland's capital impresses with its resilience, respect for history, contemporary style and sheer joie de vivre.",
                "https://www.roughguides.com/wp-content/uploads/2013/04/warsaw-old-town-royal-castle-poland-shutterstock_1171262353.jpg",
                "https://flagpedia.net/data/flags/w580/pl.png"));
        AllPlaces.add(new Place(
                "Minsk, Belarus",
                "Minsk will almost certainly surprise you. The capital of Belarus is, contrary to its dreary reputation, a progressive, modern and clean place.",
                "https://lonelyplanetimages.imgix.net/mastheads/513008517_large.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/by.png"));
        AllPlaces.add(new Place(
                "Vilnius, Lithuania",
                "There is a dreamy quality to Vilnius (vil-nyus), especially in the golden glow of a midsummer evening.",
                "https://content.r9cdn.net/rimg/dimg/09/c0/92c9d67d-city-7110-162f78f1fbc.jpg?crop=true&width=1000&height=600&xhint=2126&yhint=1263",
                "https://flagpedia.net/data/flags/w580/lt.png"));
        AllPlaces.add(new Place(
                "Riga, Latvia",
                "The Gothic spires that dominate Rīga's cityscape might suggest austerity, but it is the flamboyant art nouveau that forms the flesh and the spirit of this vibrant cosmopolitan city, the largest of all three Baltic capitals.",
                "https://www.roadaffair.com/wp-content/uploads/2019/03/house-blackheads-saint-peters-church-riga-latvia-shutterstock_1070326685-1024x683.jpg",
                "https://flagpedia.net/data/flags/w580/lv.png"));
        AllPlaces.add(new Place(
                "Tallinn, Estonia",
                "No longer the plaything of greater powers – Danish, Swedish, Polish, German and Soviet – Tallinn is now a proud European capital with an allure all of its own.",
                "https://www.nomadepicureans.com/wp-content/uploads/2019/05/Tallinn-Walking-Tour-Feature-Image.jpg",
                "https://flagpedia.net/data/flags/w580/ee.png"));
        adapter.notifyDataSetChanged();
    }

    public void addSummerWinter()
    {
        AllPlaces.clear();
        AllPlaces.add(new Place(
                "Istanbul, Turkey",
                "This magical meeting place of East and West has more top-drawer attractions than it has minarets (and that's a lot).",
                "http://blog.radissonblu.com/wp-content/uploads/2017/07/Blue-Mosque.jpg",
                "https://flagpedia.net/data/flags/w580/tr.png"));
        AllPlaces.add(new Place(
                "Rome, Italy",
                "A heady mix of haunting ruins, awe-inspiring art and vibrant street life, Italy’s hot-blooded capital is one of the world’s most romantic and charismatic cities.",
                "https://cdn.citywonders.com/media/17764/rome-city.jpg?anchor=center&mode=crop&width=1200&height=600",
                "https://flagpedia.net/data/flags/w580/it.png"));
        AllPlaces.add(new Place(
                "Berlin, Germany",
                "Berlin's combo of glamour and grit is bound to mesmerise all those keen to explore its vibrant culture, cutting-edge architecture, fabulous food, intense parties and tangible history.",
                "https://www.berlin-welcomecard.de/sites/default/files/styles/stage_desktop_20x/public/berlin-welcomecard_hero_2880__1.jpg?itok=fgbQwjEO&timestamp=1528123533",
                "https://flagpedia.net/data/flags/ultra/de.png"));
        AllPlaces.add(new Place(
                "Paris, France",
                "Paris' monument-lined boulevards, museums, classical bistros and boutiques are enhanced by a new wave of multimedia galleries, creative wine bars, design shops and tech start-ups.",
                "https://www.fodors.com/wp-content/uploads/2018/10/HERO_UltimateParis_Heroshutterstock_112137761.jpg",
                "https://flagpedia.net/data/flags/ultra/fr.png"));
        AllPlaces.add(new Place(
                "Lisbon, Portugal",
                "Seven cinematic hillsides overlooking the Rio Tejo cradle Lisbon's postcard-perfect panorama of cobbled alleyways, ancient ruins and white-domed cathedrals a captivating scene crafted over centuries.",
                "https://www.lisbonguru.com/wp-content/uploads/2015/11/viewpoints-lisbon.jpg",
                "https://flagpedia.net/data/flags/ultra/pt.png"));
        AllPlaces.add(new Place(
                "Madrid, Spain",
                "Madrid is a beguiling place with an energy that carries one simple message: this city really knows how to live.",
                "https://avisassets.abgemea.com/dam/jcr:afda6cf9-fc35-4c47-940a-baa594ab04ed/Grand_Via_Madrid_Credit_iStock_kasto80.jpg",
                "https://flagpedia.net/data/flags/ultra/es.png"));
        AllPlaces.add(new Place(
                "London, United Kingdom",
                "Immersed in history, London's rich seams of eye-opening antiquity are everywhere.",
                "http://qarva.com/wp-content/gallery/ott-2018/london-background-14.jpeg",
                "https://flagpedia.net/data/flags/ultra/gb.png"));
        AllPlaces.add(new Place(
                "Santorini, Greece",
                "Santorini is the supermodel of the Greek islands, a head-turner whose face is instantly recognisable around the world: multicoloured cliffs soar out of a sea-drowned caldera, topped by drifts of whitewashed buildings.",
                "https://dak95nwic4sny.cloudfront.net/73/santorini-44228168-1550653710-ImageGalleryLightboxLarge.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Flag_of_Greece.svg/2000px-Flag_of_Greece.svg.png"));
        AllPlaces.add(new Place(
                "Helsinki, Finland",
                "Entwined with the Baltic's bays, inlets and islands, Helsinki's boulevards and backstreets overflow with magnificent architecture, intriguing drinking and dining venues and groundbreaking design.",
                "http://www.hel.fi/wps/wcm/connect/5a20f5c2-6516-40e7-a239-ef6585fdfcfd/englkielisetpalvelutuutiskuva.jpg?MOD=AJPERES&CACHEID=ROOTWORKSPACE-5a20f5c2-6516-40e7-a239-ef6585fdfcfd-mz18k6W",
                "https://flagpedia.net/data/flags/w580/fi.png"));
        AllPlaces.add(new Place(
                "Stockholm, Sweden",
                "Stockholmers call their city 'beauty on water'. But despite the well-preserved historic core, Stockholm is no museum piece: it's modern, dynamic and ever-evolving.",
                "https://a.cdn-hotels.com/gdcs/production151/d1898/abc9d800-c31d-11e8-825c-0242ac110006.jpg",
                "https://flagpedia.net/data/flags/w580/se.png"));
        AllPlaces.add(new Place(
                "Reykjavik, Iceland",
                "The world’s most northerly capital combines colourful buildings, quirky, creative people, eye-popping design, wild nightlife and a capricious soul.",
                "https://www.roughguides.com/wp-content/uploads/2013/01/hallgrimskirkja-cathedral-city-reykjavik-iceland-shutterstock_613997816.jpg",
                "https://flagpedia.net/data/flags/w580/is.png"));
        AllPlaces.add(new Place(
                "Oslo, Norway",
                "Surrounded by mountains and sea, this compact, cultured and fun city has a palpable sense of reinvention.",
                "https://cdn-image.travelandleisure.com/sites/default/files/styles/1600x1000/public/1498156851/opera-house-oslo-norway-OSLO0617.jpg?itok=rcuoL2da",
                "https://flagpedia.net/data/flags/w580/no.png"));
        AllPlaces.add(new Place(
                "Copenhagen, Denmark",
                "Copenhagen is the epitome of Scandi cool. Modernist lamps light New Nordic tables, bridges buzz with cycling commuters and eye-candy locals dive into pristine waterways.",
                "https://lonelyplanetimages.imgix.net/mastheads/GettyImages-126140612_super%20.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/dk.png"));
        AllPlaces.add(new Place(
                "Amsterdam, Netherlands",
                "Golden Age canals lined by tilting gabled buildings are the backdrop for Amsterdam's treasure-packed museums, vintage-filled shops and hyper-creative drinking, dining and design scenes.",
                "http://cdn.cnn.com/cnnnext/dam/assets/190315131613-07-netherlands-happiest-coutries-2018.jpg",
                "https://flagpedia.net/data/flags/w580/nl.png"));
        AllPlaces.add(new Place(
                "Dublin, Ireland",
                "A small capital with a huge reputation, Dublin has a mix of heritage and hedonism that will not disappoint. All you have to do is show up.",
                "https://thesefootballtimes.co/wp-content/uploads/2015/10/Dublin-Ireland.jpg",
                "https://flagpedia.net/data/flags/w580/ie.png"));
        AllPlaces.add(new Place(
                "Brussels, Belgium",
                "Historic yet hip, bureaucratic yet bizarre, self-confident yet unshowy, Brussels is multicultural to its roots.",
                "https://media.timeout.com/images/105237721/image.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/Flag_of_Belgium.svg/2000px-Flag_of_Belgium.svg.png"));
        adapter.notifyDataSetChanged();
    }

    public void addAutumnWinter()
    {
        AllPlaces.clear();
        AllPlaces.add(new Place(
                "Chisinau, Moldova",
                "Pretty much all roads in Moldova lead to its wine-and-food loving capital.",
                "https://d1z15fh6odiy9s.cloudfront.net/plonk/adfb.../adfba780ee49dd02aa788772e39b7b44497517c80233d3e1cbad1c138f694a02-large.jpg",
                "https://flagpedia.net/data/flags/w580/md.png"));
        AllPlaces.add(new Place(
                "Bucharest, Romania",
                "Romania’s capital sometimes gets a bad rap, but in fact it's dynamic, energetic and lots of fun. Many travellers give the city just a night or two before heading off to Transylvania, but that’s not enough time.",
                "https://www.romania-insider.com/sites/default/files/styles/article_large_image/public/2019-02/bucharest_universitatii_square_-_shutterstock.jpg",
                "https://flagpedia.net/data/flags/w580/ro.png"));
        AllPlaces.add(new Place(
                "Budapest, Hungary",
                "Budapest has something for everyone – from dramatic history and flamboyant architecture to healing thermal waters and a nightlife that is unrivalled in Eastern and Central Europe.",
                "https://brightcove04pmdo-a.akamaihd.net/5104226627001/5104226627001_5515999951001_5214933106001-vs.jpg?pubId=5104226627001&videoId=5214933106001",
                "https://flagpedia.net/data/flags/w580/hu.png"));
        AllPlaces.add(new Place(
                "Bern, Switzerland",
                "Wandering through the picture-postcard, Unesco World Heritage–listed Old Town, with its provincial, laid-back air, it's hard to believe that Bern (Berne in French) is the capital of Switzerland.",
                "https://d2mpqlmtgl1znu.cloudfront.net/AcuCustom/Sitename/DAM/017/Bern_Adobe.jpg",
                "https://flagpedia.net/data/flags/w580/ch.png"));
        AllPlaces.add(new Place(
                "Vienna, Austria",
                "Baroque streetscapes and imperial palaces set the stage for Vienna's artistic and musical masterpieces alongside its coffee-house culture and vibrant epicurean and design scenes.",
                "https://img.theculturetrip.com/x/smart/wp-content/uploads/2019/04/eur-austria-vienna.jpg",
                "https://flagpedia.net/data/flags/w580/at.png"));
        AllPlaces.add(new Place(
                "Bratislava, Slovakia",
                "Slovakia's capital since the country's independence in 1993, Bratislava is a mosaic of illustrious history: a medieval and Gothic old town, baroque palaces commissioned by Hungarian nobles, and the crowning castle, rebuilt to Renaissance finery.",
                "https://s27363.pcdn.co/wp-content/uploads/2018/08/Bratislava-Slovakia-1129x752.jpg.optimal.jpg",
                "https://flagpedia.net/data/flags/w580/sk.png"));
        AllPlaces.add(new Place(
                "Prague, Czech Republic",
                "Prague is the equal of Paris in terms of beauty. Its history goes back a millennium. And the beer? The best in Europe.",
                "https://cdn.thecrazytourist.com/wp-content/uploads/2018/06/ccimage-shutterstock_428804917.jpg",
                "https://flagpedia.net/data/flags/w580/cz.png"));
        AllPlaces.add(new Place(
                "Kiev, Ukraine",
                "Long before Ukraine and Russia existed, the city's inhabitants were already striding up and down the green hills, idling hot afternoons away on the Dnipro River and promenading along Khreshchatyk then a stream, now the main avenue.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/a6469f3993128cbcdde6e2b0a90aa1d3-kyiv.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/ua.png"));
        AllPlaces.add(new Place(
                "Warsaw, Poland",
                "A phoenix arisen from the ashes, Poland's capital impresses with its resilience, respect for history, contemporary style and sheer joie de vivre.",
                "https://www.roughguides.com/wp-content/uploads/2013/04/warsaw-old-town-royal-castle-poland-shutterstock_1171262353.jpg",
                "https://flagpedia.net/data/flags/w580/pl.png"));
        AllPlaces.add(new Place(
                "Minsk, Belarus",
                "Minsk will almost certainly surprise you. The capital of Belarus is, contrary to its dreary reputation, a progressive, modern and clean place.",
                "https://lonelyplanetimages.imgix.net/mastheads/513008517_large.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/by.png"));
        AllPlaces.add(new Place(
                "Vilnius, Lithuania",
                "There is a dreamy quality to Vilnius (vil-nyus), especially in the golden glow of a midsummer evening.",
                "https://content.r9cdn.net/rimg/dimg/09/c0/92c9d67d-city-7110-162f78f1fbc.jpg?crop=true&width=1000&height=600&xhint=2126&yhint=1263",
                "https://flagpedia.net/data/flags/w580/lt.png"));
        AllPlaces.add(new Place(
                "Riga, Latvia",
                "The Gothic spires that dominate Rīga's cityscape might suggest austerity, but it is the flamboyant art nouveau that forms the flesh and the spirit of this vibrant cosmopolitan city, the largest of all three Baltic capitals.",
                "https://www.roadaffair.com/wp-content/uploads/2019/03/house-blackheads-saint-peters-church-riga-latvia-shutterstock_1070326685-1024x683.jpg",
                "https://flagpedia.net/data/flags/w580/lv.png"));
        AllPlaces.add(new Place(
                "Tallinn, Estonia",
                "No longer the plaything of greater powers – Danish, Swedish, Polish, German and Soviet – Tallinn is now a proud European capital with an allure all of its own.",
                "https://www.nomadepicureans.com/wp-content/uploads/2019/05/Tallinn-Walking-Tour-Feature-Image.jpg",
                "https://flagpedia.net/data/flags/w580/ee.png"));
        AllPlaces.add(new Place(
                "Helsinki, Finland",
                "Entwined with the Baltic's bays, inlets and islands, Helsinki's boulevards and backstreets overflow with magnificent architecture, intriguing drinking and dining venues and groundbreaking design.",
                "http://www.hel.fi/wps/wcm/connect/5a20f5c2-6516-40e7-a239-ef6585fdfcfd/englkielisetpalvelutuutiskuva.jpg?MOD=AJPERES&CACHEID=ROOTWORKSPACE-5a20f5c2-6516-40e7-a239-ef6585fdfcfd-mz18k6W",
                "https://flagpedia.net/data/flags/w580/fi.png"));
        AllPlaces.add(new Place(
                "Stockholm, Sweden",
                "Stockholmers call their city 'beauty on water'. But despite the well-preserved historic core, Stockholm is no museum piece: it's modern, dynamic and ever-evolving.",
                "https://a.cdn-hotels.com/gdcs/production151/d1898/abc9d800-c31d-11e8-825c-0242ac110006.jpg",
                "https://flagpedia.net/data/flags/w580/se.png"));
        AllPlaces.add(new Place(
                "Reykjavik, Iceland",
                "The world’s most northerly capital combines colourful buildings, quirky, creative people, eye-popping design, wild nightlife and a capricious soul.",
                "https://www.roughguides.com/wp-content/uploads/2013/01/hallgrimskirkja-cathedral-city-reykjavik-iceland-shutterstock_613997816.jpg",
                "https://flagpedia.net/data/flags/w580/is.png"));
        AllPlaces.add(new Place(
                "Oslo, Norway",
                "Surrounded by mountains and sea, this compact, cultured and fun city has a palpable sense of reinvention.",
                "https://cdn-image.travelandleisure.com/sites/default/files/styles/1600x1000/public/1498156851/opera-house-oslo-norway-OSLO0617.jpg?itok=rcuoL2da",
                "https://flagpedia.net/data/flags/w580/no.png"));
        AllPlaces.add(new Place(
                "Copenhagen, Denmark",
                "Copenhagen is the epitome of Scandi cool. Modernist lamps light New Nordic tables, bridges buzz with cycling commuters and eye-candy locals dive into pristine waterways.",
                "https://lonelyplanetimages.imgix.net/mastheads/GettyImages-126140612_super%20.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/dk.png"));
        AllPlaces.add(new Place(
                "Amsterdam, Netherlands",
                "Golden Age canals lined by tilting gabled buildings are the backdrop for Amsterdam's treasure-packed museums, vintage-filled shops and hyper-creative drinking, dining and design scenes.",
                "http://cdn.cnn.com/cnnnext/dam/assets/190315131613-07-netherlands-happiest-coutries-2018.jpg",
                "https://flagpedia.net/data/flags/w580/nl.png"));
        AllPlaces.add(new Place(
                "Dublin, Ireland",
                "A small capital with a huge reputation, Dublin has a mix of heritage and hedonism that will not disappoint. All you have to do is show up.",
                "https://thesefootballtimes.co/wp-content/uploads/2015/10/Dublin-Ireland.jpg",
                "https://flagpedia.net/data/flags/w580/ie.png"));
        AllPlaces.add(new Place(
                "Brussels, Belgium",
                "Historic yet hip, bureaucratic yet bizarre, self-confident yet unshowy, Brussels is multicultural to its roots.",
                "https://media.timeout.com/images/105237721/image.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/Flag_of_Belgium.svg/2000px-Flag_of_Belgium.svg.png"));
        adapter.notifyDataSetChanged();
    }

//    CONTINENTS FILTER

    public void addEurope()
    {
        AllPlaces.clear();
        AllPlaces.add(new Place(
                "Skopje, Macedonia",
                "Skopje has plenty of charm. Its Ottoman- and Byzantine-era sights are focused around the city's delightful Čaršija, bordered by the 15th-century Kameni Most (Stone Bridge) and Tvrdina Kale Fortress – Skopje's guardian since the 5th century.",
                "https://lonelyplanetimages.imgix.net/mastheads/83405857%20.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/mk.png"));
        AllPlaces.add(new Place(
                "Pristina, Kosovo",
                "Pristina is a fast-changing city that feels full of optimism and potential, even if its traffic-clogged streets and mismatched architectural styles don't make it an obviously attractive place.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/e228bf3be784ffff7a338ec8d9167d30-pristina.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/ks.png"));
        AllPlaces.add(new Place(
                "Tirana, Albania",
                "Lively, colourful Tirana is where this tiny nation's hopes and dreams coalesce into a vibrant whirl of traffic, brash consumerism and unfettered fun.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/25e80d94ce0c6dfee0634a2b8598feae-tirana.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/al.png"));
        AllPlaces.add(new Place(
                "Podgorica, Montenegro",
                "Given it’s undergone five name changes, passed through the hands of everyone from the Romans to the Turks to the Austro-Hungarians, and twice been wiped off the map entirely, it’s little wonder that Podgorica (Подгорица) seems permanently gripped by an identity crisis.",
                "https://lonelyplanetimages.imgix.net/mastheads/147031066_medium.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/me.png"));
        AllPlaces.add(new Place(
                "Sarajevo, Bosnia Herzegovina",
                "Ringed by mountains, Sarajevo is a singular city with an enticing East-meets-West vibe all of its own. It was once renowned as a religious melting pot, earning it the epithet 'the Jerusalem of Europe'.",
                "https://s23835.pcdn.co/wp-content/uploads/2018/07/Bosnia-Sarajevo-view-4.jpg",
                "https://flagpedia.net/data/flags/w580/ba.png"));
        AllPlaces.add(new Place(
                "Belgrade, Serbia",
                "Outspoken, adventurous, proud and audacious: Belgrade ('White City') is by no means a 'pretty' capital, but its gritty exuberance makes it one of Europe's most happening cities.",
                "https://media.architecturaldigest.com/photos/5d37554e47a77b000872bbf4/16:9/w_1280,c_limit/GettyImages-847708760.jpg",
                "https://flagpedia.net/data/flags/w580/rs.png"));
        AllPlaces.add(new Place(
                "Zagreb, Croatia",
                "Zagreb is made for strolling. Wander through the Upper Town's red-roof and cobblestone glory, peppered with church spires.",
                "https://brightcove04pmdo-a.akamaihd.net/5104226627001/5104226627001_5441113691001_5428833703001-vs.jpg?pubId=5104226627001&videoId=5428833703001",
                "https://flagpedia.net/data/flags/w580/hr.png"));
        AllPlaces.add(new Place(
                "Ljubljana, Slovenia",
                "Slovenia's capital and largest city is one of Europe's greenest and most liveable capitals; it was the European Commission's Green Capital of Europe in 2016.",
                "https://lonelyplanetimages.imgix.net/mastheads/GettyImages-494314513_high.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/si.png"));
        AllPlaces.add(new Place(
                "Sofia, Bulgaria",
                "Bulgaria's pleasingly laid-back capital is often overlooked by visitors heading to the coast or the ski resorts, but they're missing something special.",
                "https://happytowander.com/wp-content/uploads/Sofia-Bulgaria-Happy-to-Wander-0609.jpg",
                "https://flagpedia.net/data/flags/w580/bg.png"));
        AllPlaces.add(new Place(
                "Istanbul, Turkey",
                "This magical meeting place of East and West has more top-drawer attractions than it has minarets (and that's a lot).",
                "http://blog.radissonblu.com/wp-content/uploads/2017/07/Blue-Mosque.jpg",
                "https://flagpedia.net/data/flags/w580/tr.png"));
        AllPlaces.add(new Place(
                "Chisinau, Moldova",
                "Pretty much all roads in Moldova lead to its wine-and-food loving capital.",
                "https://d1z15fh6odiy9s.cloudfront.net/plonk/adfb.../adfba780ee49dd02aa788772e39b7b44497517c80233d3e1cbad1c138f694a02-large.jpg",
                "https://flagpedia.net/data/flags/w580/md.png"));
        AllPlaces.add(new Place(
                "Bucharest, Romania",
                "Romania’s capital sometimes gets a bad rap, but in fact it's dynamic, energetic and lots of fun. Many travellers give the city just a night or two before heading off to Transylvania, but that’s not enough time.",
                "https://www.romania-insider.com/sites/default/files/styles/article_large_image/public/2019-02/bucharest_universitatii_square_-_shutterstock.jpg",
                "https://flagpedia.net/data/flags/w580/ro.png"));
        AllPlaces.add(new Place(
                "Budapest, Hungary",
                "Budapest has something for everyone – from dramatic history and flamboyant architecture to healing thermal waters and a nightlife that is unrivalled in Eastern and Central Europe.",
                "https://brightcove04pmdo-a.akamaihd.net/5104226627001/5104226627001_5515999951001_5214933106001-vs.jpg?pubId=5104226627001&videoId=5214933106001",
                "https://flagpedia.net/data/flags/w580/hu.png"));
        AllPlaces.add(new Place(
                "Rome, Italy",
                "A heady mix of haunting ruins, awe-inspiring art and vibrant street life, Italy’s hot-blooded capital is one of the world’s most romantic and charismatic cities.",
                "https://cdn.citywonders.com/media/17764/rome-city.jpg?anchor=center&mode=crop&width=1200&height=600",
                "https://flagpedia.net/data/flags/w580/it.png"));
        AllPlaces.add(new Place(
                "Bern, Switzerland",
                "Wandering through the picture-postcard, Unesco World Heritage–listed Old Town, with its provincial, laid-back air, it's hard to believe that Bern (Berne in French) is the capital of Switzerland.",
                "https://d2mpqlmtgl1znu.cloudfront.net/AcuCustom/Sitename/DAM/017/Bern_Adobe.jpg",
                "https://flagpedia.net/data/flags/w580/ch.png"));
        AllPlaces.add(new Place(
                "Vienna, Austria",
                "Baroque streetscapes and imperial palaces set the stage for Vienna's artistic and musical masterpieces alongside its coffee-house culture and vibrant epicurean and design scenes.",
                "https://img.theculturetrip.com/x/smart/wp-content/uploads/2019/04/eur-austria-vienna.jpg",
                "https://flagpedia.net/data/flags/w580/at.png"));
        AllPlaces.add(new Place(
                "Bratislava, Slovakia",
                "Slovakia's capital since the country's independence in 1993, Bratislava is a mosaic of illustrious history: a medieval and Gothic old town, baroque palaces commissioned by Hungarian nobles, and the crowning castle, rebuilt to Renaissance finery.",
                "https://s27363.pcdn.co/wp-content/uploads/2018/08/Bratislava-Slovakia-1129x752.jpg.optimal.jpg",
                "https://flagpedia.net/data/flags/w580/sk.png"));
        AllPlaces.add(new Place(
                "Prague, Czech Republic",
                "Prague is the equal of Paris in terms of beauty. Its history goes back a millennium. And the beer? The best in Europe.",
                "https://cdn.thecrazytourist.com/wp-content/uploads/2018/06/ccimage-shutterstock_428804917.jpg",
                "https://flagpedia.net/data/flags/w580/cz.png"));
        AllPlaces.add(new Place(
                "Kiev, Ukraine",
                "Long before Ukraine and Russia existed, the city's inhabitants were already striding up and down the green hills, idling hot afternoons away on the Dnipro River and promenading along Khreshchatyk then a stream, now the main avenue.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/a6469f3993128cbcdde6e2b0a90aa1d3-kyiv.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/ua.png"));
        AllPlaces.add(new Place(
                "Warsaw, Poland",
                "A phoenix arisen from the ashes, Poland's capital impresses with its resilience, respect for history, contemporary style and sheer joie de vivre.",
                "https://www.roughguides.com/wp-content/uploads/2013/04/warsaw-old-town-royal-castle-poland-shutterstock_1171262353.jpg",
                "https://flagpedia.net/data/flags/w580/pl.png"));
        AllPlaces.add(new Place(
                "Minsk, Belarus",
                "Minsk will almost certainly surprise you. The capital of Belarus is, contrary to its dreary reputation, a progressive, modern and clean place.",
                "https://lonelyplanetimages.imgix.net/mastheads/513008517_large.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/by.png"));
        AllPlaces.add(new Place(
                "Vilnius, Lithuania",
                "There is a dreamy quality to Vilnius (vil-nyus), especially in the golden glow of a midsummer evening.",
                "https://content.r9cdn.net/rimg/dimg/09/c0/92c9d67d-city-7110-162f78f1fbc.jpg?crop=true&width=1000&height=600&xhint=2126&yhint=1263",
                "https://flagpedia.net/data/flags/w580/lt.png"));
        AllPlaces.add(new Place(
                "Riga, Latvia",
                "The Gothic spires that dominate Rīga's cityscape might suggest austerity, but it is the flamboyant art nouveau that forms the flesh and the spirit of this vibrant cosmopolitan city, the largest of all three Baltic capitals.",
                "https://www.roadaffair.com/wp-content/uploads/2019/03/house-blackheads-saint-peters-church-riga-latvia-shutterstock_1070326685-1024x683.jpg",
                "https://flagpedia.net/data/flags/w580/lv.png"));
        AllPlaces.add(new Place(
                "Tallinn, Estonia",
                "No longer the plaything of greater powers – Danish, Swedish, Polish, German and Soviet – Tallinn is now a proud European capital with an allure all of its own.",
                "https://www.nomadepicureans.com/wp-content/uploads/2019/05/Tallinn-Walking-Tour-Feature-Image.jpg",
                "https://flagpedia.net/data/flags/w580/ee.png"));
        AllPlaces.add(new Place(
                "Helsinki, Finland",
                "Entwined with the Baltic's bays, inlets and islands, Helsinki's boulevards and backstreets overflow with magnificent architecture, intriguing drinking and dining venues and groundbreaking design.",
                "http://www.hel.fi/wps/wcm/connect/5a20f5c2-6516-40e7-a239-ef6585fdfcfd/englkielisetpalvelutuutiskuva.jpg?MOD=AJPERES&CACHEID=ROOTWORKSPACE-5a20f5c2-6516-40e7-a239-ef6585fdfcfd-mz18k6W",
                "https://flagpedia.net/data/flags/w580/fi.png"));
        AllPlaces.add(new Place(
                "Stockholm, Sweden",
                "Stockholmers call their city 'beauty on water'. But despite the well-preserved historic core, Stockholm is no museum piece: it's modern, dynamic and ever-evolving.",
                "https://a.cdn-hotels.com/gdcs/production151/d1898/abc9d800-c31d-11e8-825c-0242ac110006.jpg",
                "https://flagpedia.net/data/flags/w580/se.png"));
        AllPlaces.add(new Place(
                "Reykjavik, Iceland",
                "The world’s most northerly capital combines colourful buildings, quirky, creative people, eye-popping design, wild nightlife and a capricious soul.",
                "https://www.roughguides.com/wp-content/uploads/2013/01/hallgrimskirkja-cathedral-city-reykjavik-iceland-shutterstock_613997816.jpg",
                "https://flagpedia.net/data/flags/w580/is.png"));
        AllPlaces.add(new Place(
                "Oslo, Norway",
                "Surrounded by mountains and sea, this compact, cultured and fun city has a palpable sense of reinvention.",
                "https://cdn-image.travelandleisure.com/sites/default/files/styles/1600x1000/public/1498156851/opera-house-oslo-norway-OSLO0617.jpg?itok=rcuoL2da",
                "https://flagpedia.net/data/flags/w580/no.png"));
        AllPlaces.add(new Place(
                "Copenhagen, Denmark",
                "Copenhagen is the epitome of Scandi cool. Modernist lamps light New Nordic tables, bridges buzz with cycling commuters and eye-candy locals dive into pristine waterways.",
                "https://lonelyplanetimages.imgix.net/mastheads/GettyImages-126140612_super%20.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/dk.png"));
        AllPlaces.add(new Place(
                "Amsterdam, Netherlands",
                "Golden Age canals lined by tilting gabled buildings are the backdrop for Amsterdam's treasure-packed museums, vintage-filled shops and hyper-creative drinking, dining and design scenes.",
                "http://cdn.cnn.com/cnnnext/dam/assets/190315131613-07-netherlands-happiest-coutries-2018.jpg",
                "https://flagpedia.net/data/flags/w580/nl.png"));
        AllPlaces.add(new Place(
                "Dublin, Ireland",
                "A small capital with a huge reputation, Dublin has a mix of heritage and hedonism that will not disappoint. All you have to do is show up.",
                "https://thesefootballtimes.co/wp-content/uploads/2015/10/Dublin-Ireland.jpg",
                "https://flagpedia.net/data/flags/w580/ie.png"));
        AllPlaces.add(new Place(
                "Brussels, Belgium",
                "Historic yet hip, bureaucratic yet bizarre, self-confident yet unshowy, Brussels is multicultural to its roots.",
                "https://media.timeout.com/images/105237721/image.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/Flag_of_Belgium.svg/2000px-Flag_of_Belgium.svg.png"));
        AllPlaces.add(new Place(
                "Berlin, Germany",
                "Berlin's combo of glamour and grit is bound to mesmerise all those keen to explore its vibrant culture, cutting-edge architecture, fabulous food, intense parties and tangible history.",
                "https://www.berlin-welcomecard.de/sites/default/files/styles/stage_desktop_20x/public/berlin-welcomecard_hero_2880__1.jpg?itok=fgbQwjEO&timestamp=1528123533",
                "https://flagpedia.net/data/flags/ultra/de.png"));
        AllPlaces.add(new Place(
                "Paris, France",
                "Paris' monument-lined boulevards, museums, classical bistros and boutiques are enhanced by a new wave of multimedia galleries, creative wine bars, design shops and tech start-ups.",
                "https://www.fodors.com/wp-content/uploads/2018/10/HERO_UltimateParis_Heroshutterstock_112137761.jpg",
                "https://flagpedia.net/data/flags/ultra/fr.png"));
        AllPlaces.add(new Place(
                "Lisbon, Portugal",
                "Seven cinematic hillsides overlooking the Rio Tejo cradle Lisbon's postcard-perfect panorama of cobbled alleyways, ancient ruins and white-domed cathedrals a captivating scene crafted over centuries.",
                "https://www.lisbonguru.com/wp-content/uploads/2015/11/viewpoints-lisbon.jpg",
                "https://flagpedia.net/data/flags/ultra/pt.png"));
        AllPlaces.add(new Place(
                "Madrid, Spain",
                "Madrid is a beguiling place with an energy that carries one simple message: this city really knows how to live.",
                "https://avisassets.abgemea.com/dam/jcr:afda6cf9-fc35-4c47-940a-baa594ab04ed/Grand_Via_Madrid_Credit_iStock_kasto80.jpg",
                "https://flagpedia.net/data/flags/ultra/es.png"));
        AllPlaces.add(new Place(
                "London, United Kingdom",
                "Immersed in history, London's rich seams of eye-opening antiquity are everywhere.",
                "http://qarva.com/wp-content/gallery/ott-2018/london-background-14.jpeg",
                "https://flagpedia.net/data/flags/ultra/gb.png"));
        AllPlaces.add(new Place(
                "Santorini, Greece",
                "Santorini is the supermodel of the Greek islands, a head-turner whose face is instantly recognisable around the world: multicoloured cliffs soar out of a sea-drowned caldera, topped by drifts of whitewashed buildings.",
                "https://dak95nwic4sny.cloudfront.net/73/santorini-44228168-1550653710-ImageGalleryLightboxLarge.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Flag_of_Greece.svg/2000px-Flag_of_Greece.svg.png"));
        adapter.notifyDataSetChanged();
    }

    public void addAsia()
    {
        AllPlaces.clear();
        AllPlaces.add(new Place(
                "Palawan, Philippines",
                "Nothing defines Palawan more than the water around it.",
                "https://www.leisurepro.com/blog/wp-content/uploads/2019/01/shutterstock_587590205-1366x800@2x.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/99/Flag_of_the_Philippines.svg/2000px-Flag_of_the_Philippines.svg.png"));
        adapter.notifyDataSetChanged();
    }

    public void addAustralia()
    {
        AllPlaces.clear();
        AllPlaces.add(new Place(
                "Sydney, Australia",
                "Sydney, spectacularly draped around its glorious harbour and beaches, has visual wow factor like few other cities. Scratch the surface and it only gets better.",
                "https://www.tourstogo.com.au/things-to-do/wp-content/uploads/2018/12/Sydney-Featured.jpg",
                "https://static.grainger.com/rp/s/is/image/Grainger/5JFT9_AL01"));
        adapter.notifyDataSetChanged();
    }

    public void addAfrica()
    {
        AllPlaces.clear();
        AllPlaces.add(new Place(
                "Nairobi, Kenya",
                "East Africa's most cosmopolitan city, Nairobi is Kenya's beating heart, an exciting, maddening concrete jungle that jarringly counterpoints the untrammelled natural beauty to be found elsewhere in the country.",
                "https://dak95nwic4sny.cloudfront.net/73/kenya-beach-and-safari-43962495-1554105066-ImageGalleryLightboxLarge.jpg",
                "https://flagpedia.net/data/flags/w580/ke.png"));
        adapter.notifyDataSetChanged();
    }

    public void addSouthAmerica()
    {
        AllPlaces.clear();
        AllPlaces.add(new Place(
                "Buenos Aires, Argentina",
                "Beautiful, defiant and intense, Argentina seduces with its streetside tango, wafting grills, fútbol (soccer), gaucho culture and the mighty Andes. It's one formidable cocktail of wanderlust.",
                "https://d1bvpoagx8hqbg.cloudfront.net/originals/experience-buenos-aires-argentina-evelyn-4f019adee7f47b76494b404b3553e354.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1a/Flag_of_Argentina.svg/2000px-Flag_of_Argentina.svg.png"));
        adapter.notifyDataSetChanged();
    }

    public void addNorthAmerica()
    {
        AllPlaces.clear();
        AllPlaces.add(new Place(
                "California, USA",
                "From misty Northern California redwood forests to sun-kissed Southern California beaches, the enchanted Golden State makes Disneyland seem normal.",
                "https://cdn.vox-cdn.com/thumbor/-8P2BKe5-vuV5wrlhVPjZNPERrU=/0x0:4288x2848/1200x800/filters:focal(1985x1801:2671x2487)/cdn.vox-cdn.com/uploads/chorus_image/image/58838825/shutterstock_604520357.0.jpg",
                "https://flagpedia.net/data/flags/w580/us.png"));
        adapter.notifyDataSetChanged();
    }

//    15 CONTINENT COMBINATIONS

    public void addEuropeAsia()
    {
        AllPlaces.clear();
        AllPlaces.add(new Place(
                "Skopje, Macedonia",
                "Skopje has plenty of charm. Its Ottoman- and Byzantine-era sights are focused around the city's delightful Čaršija, bordered by the 15th-century Kameni Most (Stone Bridge) and Tvrdina Kale Fortress – Skopje's guardian since the 5th century.",
                "https://lonelyplanetimages.imgix.net/mastheads/83405857%20.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/mk.png"));
        AllPlaces.add(new Place(
                "Pristina, Kosovo",
                "Pristina is a fast-changing city that feels full of optimism and potential, even if its traffic-clogged streets and mismatched architectural styles don't make it an obviously attractive place.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/e228bf3be784ffff7a338ec8d9167d30-pristina.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/ks.png"));
        AllPlaces.add(new Place(
                "Tirana, Albania",
                "Lively, colourful Tirana is where this tiny nation's hopes and dreams coalesce into a vibrant whirl of traffic, brash consumerism and unfettered fun.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/25e80d94ce0c6dfee0634a2b8598feae-tirana.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/al.png"));
        AllPlaces.add(new Place(
                "Podgorica, Montenegro",
                "Given it’s undergone five name changes, passed through the hands of everyone from the Romans to the Turks to the Austro-Hungarians, and twice been wiped off the map entirely, it’s little wonder that Podgorica (Подгорица) seems permanently gripped by an identity crisis.",
                "https://lonelyplanetimages.imgix.net/mastheads/147031066_medium.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/me.png"));
        AllPlaces.add(new Place(
                "Sarajevo, Bosnia Herzegovina",
                "Ringed by mountains, Sarajevo is a singular city with an enticing East-meets-West vibe all of its own. It was once renowned as a religious melting pot, earning it the epithet 'the Jerusalem of Europe'.",
                "https://s23835.pcdn.co/wp-content/uploads/2018/07/Bosnia-Sarajevo-view-4.jpg",
                "https://flagpedia.net/data/flags/w580/ba.png"));
        AllPlaces.add(new Place(
                "Belgrade, Serbia",
                "Outspoken, adventurous, proud and audacious: Belgrade ('White City') is by no means a 'pretty' capital, but its gritty exuberance makes it one of Europe's most happening cities.",
                "https://media.architecturaldigest.com/photos/5d37554e47a77b000872bbf4/16:9/w_1280,c_limit/GettyImages-847708760.jpg",
                "https://flagpedia.net/data/flags/w580/rs.png"));
        AllPlaces.add(new Place(
                "Zagreb, Croatia",
                "Zagreb is made for strolling. Wander through the Upper Town's red-roof and cobblestone glory, peppered with church spires.",
                "https://brightcove04pmdo-a.akamaihd.net/5104226627001/5104226627001_5441113691001_5428833703001-vs.jpg?pubId=5104226627001&videoId=5428833703001",
                "https://flagpedia.net/data/flags/w580/hr.png"));
        AllPlaces.add(new Place(
                "Ljubljana, Slovenia",
                "Slovenia's capital and largest city is one of Europe's greenest and most liveable capitals; it was the European Commission's Green Capital of Europe in 2016.",
                "https://lonelyplanetimages.imgix.net/mastheads/GettyImages-494314513_high.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/si.png"));
        AllPlaces.add(new Place(
                "Sofia, Bulgaria",
                "Bulgaria's pleasingly laid-back capital is often overlooked by visitors heading to the coast or the ski resorts, but they're missing something special.",
                "https://happytowander.com/wp-content/uploads/Sofia-Bulgaria-Happy-to-Wander-0609.jpg",
                "https://flagpedia.net/data/flags/w580/bg.png"));
        AllPlaces.add(new Place(
                "Istanbul, Turkey",
                "This magical meeting place of East and West has more top-drawer attractions than it has minarets (and that's a lot).",
                "http://blog.radissonblu.com/wp-content/uploads/2017/07/Blue-Mosque.jpg",
                "https://flagpedia.net/data/flags/w580/tr.png"));
        AllPlaces.add(new Place(
                "Chisinau, Moldova",
                "Pretty much all roads in Moldova lead to its wine-and-food loving capital.",
                "https://d1z15fh6odiy9s.cloudfront.net/plonk/adfb.../adfba780ee49dd02aa788772e39b7b44497517c80233d3e1cbad1c138f694a02-large.jpg",
                "https://flagpedia.net/data/flags/w580/md.png"));
        AllPlaces.add(new Place(
                "Bucharest, Romania",
                "Romania’s capital sometimes gets a bad rap, but in fact it's dynamic, energetic and lots of fun. Many travellers give the city just a night or two before heading off to Transylvania, but that’s not enough time.",
                "https://www.romania-insider.com/sites/default/files/styles/article_large_image/public/2019-02/bucharest_universitatii_square_-_shutterstock.jpg",
                "https://flagpedia.net/data/flags/w580/ro.png"));
        AllPlaces.add(new Place(
                "Budapest, Hungary",
                "Budapest has something for everyone – from dramatic history and flamboyant architecture to healing thermal waters and a nightlife that is unrivalled in Eastern and Central Europe.",
                "https://brightcove04pmdo-a.akamaihd.net/5104226627001/5104226627001_5515999951001_5214933106001-vs.jpg?pubId=5104226627001&videoId=5214933106001",
                "https://flagpedia.net/data/flags/w580/hu.png"));
        AllPlaces.add(new Place(
                "Rome, Italy",
                "A heady mix of haunting ruins, awe-inspiring art and vibrant street life, Italy’s hot-blooded capital is one of the world’s most romantic and charismatic cities.",
                "https://cdn.citywonders.com/media/17764/rome-city.jpg?anchor=center&mode=crop&width=1200&height=600",
                "https://flagpedia.net/data/flags/w580/it.png"));
        AllPlaces.add(new Place(
                "Bern, Switzerland",
                "Wandering through the picture-postcard, Unesco World Heritage–listed Old Town, with its provincial, laid-back air, it's hard to believe that Bern (Berne in French) is the capital of Switzerland.",
                "https://d2mpqlmtgl1znu.cloudfront.net/AcuCustom/Sitename/DAM/017/Bern_Adobe.jpg",
                "https://flagpedia.net/data/flags/w580/ch.png"));
        AllPlaces.add(new Place(
                "Vienna, Austria",
                "Baroque streetscapes and imperial palaces set the stage for Vienna's artistic and musical masterpieces alongside its coffee-house culture and vibrant epicurean and design scenes.",
                "https://img.theculturetrip.com/x/smart/wp-content/uploads/2019/04/eur-austria-vienna.jpg",
                "https://flagpedia.net/data/flags/w580/at.png"));
        AllPlaces.add(new Place(
                "Bratislava, Slovakia",
                "Slovakia's capital since the country's independence in 1993, Bratislava is a mosaic of illustrious history: a medieval and Gothic old town, baroque palaces commissioned by Hungarian nobles, and the crowning castle, rebuilt to Renaissance finery.",
                "https://s27363.pcdn.co/wp-content/uploads/2018/08/Bratislava-Slovakia-1129x752.jpg.optimal.jpg",
                "https://flagpedia.net/data/flags/w580/sk.png"));
        AllPlaces.add(new Place(
                "Prague, Czech Republic",
                "Prague is the equal of Paris in terms of beauty. Its history goes back a millennium. And the beer? The best in Europe.",
                "https://cdn.thecrazytourist.com/wp-content/uploads/2018/06/ccimage-shutterstock_428804917.jpg",
                "https://flagpedia.net/data/flags/w580/cz.png"));
        AllPlaces.add(new Place(
                "Kiev, Ukraine",
                "Long before Ukraine and Russia existed, the city's inhabitants were already striding up and down the green hills, idling hot afternoons away on the Dnipro River and promenading along Khreshchatyk then a stream, now the main avenue.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/a6469f3993128cbcdde6e2b0a90aa1d3-kyiv.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/ua.png"));
        AllPlaces.add(new Place(
                "Warsaw, Poland",
                "A phoenix arisen from the ashes, Poland's capital impresses with its resilience, respect for history, contemporary style and sheer joie de vivre.",
                "https://www.roughguides.com/wp-content/uploads/2013/04/warsaw-old-town-royal-castle-poland-shutterstock_1171262353.jpg",
                "https://flagpedia.net/data/flags/w580/pl.png"));
        AllPlaces.add(new Place(
                "Minsk, Belarus",
                "Minsk will almost certainly surprise you. The capital of Belarus is, contrary to its dreary reputation, a progressive, modern and clean place.",
                "https://lonelyplanetimages.imgix.net/mastheads/513008517_large.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/by.png"));
        AllPlaces.add(new Place(
                "Vilnius, Lithuania",
                "There is a dreamy quality to Vilnius (vil-nyus), especially in the golden glow of a midsummer evening.",
                "https://content.r9cdn.net/rimg/dimg/09/c0/92c9d67d-city-7110-162f78f1fbc.jpg?crop=true&width=1000&height=600&xhint=2126&yhint=1263",
                "https://flagpedia.net/data/flags/w580/lt.png"));
        AllPlaces.add(new Place(
                "Riga, Latvia",
                "The Gothic spires that dominate Rīga's cityscape might suggest austerity, but it is the flamboyant art nouveau that forms the flesh and the spirit of this vibrant cosmopolitan city, the largest of all three Baltic capitals.",
                "https://www.roadaffair.com/wp-content/uploads/2019/03/house-blackheads-saint-peters-church-riga-latvia-shutterstock_1070326685-1024x683.jpg",
                "https://flagpedia.net/data/flags/w580/lv.png"));
        AllPlaces.add(new Place(
                "Tallinn, Estonia",
                "No longer the plaything of greater powers – Danish, Swedish, Polish, German and Soviet – Tallinn is now a proud European capital with an allure all of its own.",
                "https://www.nomadepicureans.com/wp-content/uploads/2019/05/Tallinn-Walking-Tour-Feature-Image.jpg",
                "https://flagpedia.net/data/flags/w580/ee.png"));
        AllPlaces.add(new Place(
                "Helsinki, Finland",
                "Entwined with the Baltic's bays, inlets and islands, Helsinki's boulevards and backstreets overflow with magnificent architecture, intriguing drinking and dining venues and groundbreaking design.",
                "http://www.hel.fi/wps/wcm/connect/5a20f5c2-6516-40e7-a239-ef6585fdfcfd/englkielisetpalvelutuutiskuva.jpg?MOD=AJPERES&CACHEID=ROOTWORKSPACE-5a20f5c2-6516-40e7-a239-ef6585fdfcfd-mz18k6W",
                "https://flagpedia.net/data/flags/w580/fi.png"));
        AllPlaces.add(new Place(
                "Stockholm, Sweden",
                "Stockholmers call their city 'beauty on water'. But despite the well-preserved historic core, Stockholm is no museum piece: it's modern, dynamic and ever-evolving.",
                "https://a.cdn-hotels.com/gdcs/production151/d1898/abc9d800-c31d-11e8-825c-0242ac110006.jpg",
                "https://flagpedia.net/data/flags/w580/se.png"));
        AllPlaces.add(new Place(
                "Reykjavik, Iceland",
                "The world’s most northerly capital combines colourful buildings, quirky, creative people, eye-popping design, wild nightlife and a capricious soul.",
                "https://www.roughguides.com/wp-content/uploads/2013/01/hallgrimskirkja-cathedral-city-reykjavik-iceland-shutterstock_613997816.jpg",
                "https://flagpedia.net/data/flags/w580/is.png"));
        AllPlaces.add(new Place(
                "Oslo, Norway",
                "Surrounded by mountains and sea, this compact, cultured and fun city has a palpable sense of reinvention.",
                "https://cdn-image.travelandleisure.com/sites/default/files/styles/1600x1000/public/1498156851/opera-house-oslo-norway-OSLO0617.jpg?itok=rcuoL2da",
                "https://flagpedia.net/data/flags/w580/no.png"));
        AllPlaces.add(new Place(
                "Copenhagen, Denmark",
                "Copenhagen is the epitome of Scandi cool. Modernist lamps light New Nordic tables, bridges buzz with cycling commuters and eye-candy locals dive into pristine waterways.",
                "https://lonelyplanetimages.imgix.net/mastheads/GettyImages-126140612_super%20.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/dk.png"));
        AllPlaces.add(new Place(
                "Amsterdam, Netherlands",
                "Golden Age canals lined by tilting gabled buildings are the backdrop for Amsterdam's treasure-packed museums, vintage-filled shops and hyper-creative drinking, dining and design scenes.",
                "http://cdn.cnn.com/cnnnext/dam/assets/190315131613-07-netherlands-happiest-coutries-2018.jpg",
                "https://flagpedia.net/data/flags/w580/nl.png"));
        AllPlaces.add(new Place(
                "Dublin, Ireland",
                "A small capital with a huge reputation, Dublin has a mix of heritage and hedonism that will not disappoint. All you have to do is show up.",
                "https://thesefootballtimes.co/wp-content/uploads/2015/10/Dublin-Ireland.jpg",
                "https://flagpedia.net/data/flags/w580/ie.png"));
        AllPlaces.add(new Place(
                "Brussels, Belgium",
                "Historic yet hip, bureaucratic yet bizarre, self-confident yet unshowy, Brussels is multicultural to its roots.",
                "https://media.timeout.com/images/105237721/image.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/Flag_of_Belgium.svg/2000px-Flag_of_Belgium.svg.png"));
        AllPlaces.add(new Place(
                "Berlin, Germany",
                "Berlin's combo of glamour and grit is bound to mesmerise all those keen to explore its vibrant culture, cutting-edge architecture, fabulous food, intense parties and tangible history.",
                "https://www.berlin-welcomecard.de/sites/default/files/styles/stage_desktop_20x/public/berlin-welcomecard_hero_2880__1.jpg?itok=fgbQwjEO&timestamp=1528123533",
                "https://flagpedia.net/data/flags/ultra/de.png"));
        AllPlaces.add(new Place(
                "Paris, France",
                "Paris' monument-lined boulevards, museums, classical bistros and boutiques are enhanced by a new wave of multimedia galleries, creative wine bars, design shops and tech start-ups.",
                "https://www.fodors.com/wp-content/uploads/2018/10/HERO_UltimateParis_Heroshutterstock_112137761.jpg",
                "https://flagpedia.net/data/flags/ultra/fr.png"));
        AllPlaces.add(new Place(
                "Lisbon, Portugal",
                "Seven cinematic hillsides overlooking the Rio Tejo cradle Lisbon's postcard-perfect panorama of cobbled alleyways, ancient ruins and white-domed cathedrals a captivating scene crafted over centuries.",
                "https://www.lisbonguru.com/wp-content/uploads/2015/11/viewpoints-lisbon.jpg",
                "https://flagpedia.net/data/flags/ultra/pt.png"));
        AllPlaces.add(new Place(
                "Madrid, Spain",
                "Madrid is a beguiling place with an energy that carries one simple message: this city really knows how to live.",
                "https://avisassets.abgemea.com/dam/jcr:afda6cf9-fc35-4c47-940a-baa594ab04ed/Grand_Via_Madrid_Credit_iStock_kasto80.jpg",
                "https://flagpedia.net/data/flags/ultra/es.png"));
        AllPlaces.add(new Place(
                "London, United Kingdom",
                "Immersed in history, London's rich seams of eye-opening antiquity are everywhere.",
                "http://qarva.com/wp-content/gallery/ott-2018/london-background-14.jpeg",
                "https://flagpedia.net/data/flags/ultra/gb.png"));
        AllPlaces.add(new Place(
                "Santorini, Greece",
                "Santorini is the supermodel of the Greek islands, a head-turner whose face is instantly recognisable around the world: multicoloured cliffs soar out of a sea-drowned caldera, topped by drifts of whitewashed buildings.",
                "https://dak95nwic4sny.cloudfront.net/73/santorini-44228168-1550653710-ImageGalleryLightboxLarge.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Flag_of_Greece.svg/2000px-Flag_of_Greece.svg.png"));
        AllPlaces.add(new Place(
                "Palawan, Philippines",
                "Nothing defines Palawan more than the water around it.",
                "https://www.leisurepro.com/blog/wp-content/uploads/2019/01/shutterstock_587590205-1366x800@2x.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/99/Flag_of_the_Philippines.svg/2000px-Flag_of_the_Philippines.svg.png"));
        adapter.notifyDataSetChanged();
    }

    public void addEuropeAustralia()
    {
        AllPlaces.clear();
        AllPlaces.add(new Place(
                "Skopje, Macedonia",
                "Skopje has plenty of charm. Its Ottoman- and Byzantine-era sights are focused around the city's delightful Čaršija, bordered by the 15th-century Kameni Most (Stone Bridge) and Tvrdina Kale Fortress – Skopje's guardian since the 5th century.",
                "https://lonelyplanetimages.imgix.net/mastheads/83405857%20.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/mk.png"));
        AllPlaces.add(new Place(
                "Pristina, Kosovo",
                "Pristina is a fast-changing city that feels full of optimism and potential, even if its traffic-clogged streets and mismatched architectural styles don't make it an obviously attractive place.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/e228bf3be784ffff7a338ec8d9167d30-pristina.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/ks.png"));
        AllPlaces.add(new Place(
                "Tirana, Albania",
                "Lively, colourful Tirana is where this tiny nation's hopes and dreams coalesce into a vibrant whirl of traffic, brash consumerism and unfettered fun.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/25e80d94ce0c6dfee0634a2b8598feae-tirana.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/al.png"));
        AllPlaces.add(new Place(
                "Podgorica, Montenegro",
                "Given it’s undergone five name changes, passed through the hands of everyone from the Romans to the Turks to the Austro-Hungarians, and twice been wiped off the map entirely, it’s little wonder that Podgorica (Подгорица) seems permanently gripped by an identity crisis.",
                "https://lonelyplanetimages.imgix.net/mastheads/147031066_medium.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/me.png"));
        AllPlaces.add(new Place(
                "Sarajevo, Bosnia Herzegovina",
                "Ringed by mountains, Sarajevo is a singular city with an enticing East-meets-West vibe all of its own. It was once renowned as a religious melting pot, earning it the epithet 'the Jerusalem of Europe'.",
                "https://s23835.pcdn.co/wp-content/uploads/2018/07/Bosnia-Sarajevo-view-4.jpg",
                "https://flagpedia.net/data/flags/w580/ba.png"));
        AllPlaces.add(new Place(
                "Belgrade, Serbia",
                "Outspoken, adventurous, proud and audacious: Belgrade ('White City') is by no means a 'pretty' capital, but its gritty exuberance makes it one of Europe's most happening cities.",
                "https://media.architecturaldigest.com/photos/5d37554e47a77b000872bbf4/16:9/w_1280,c_limit/GettyImages-847708760.jpg",
                "https://flagpedia.net/data/flags/w580/rs.png"));
        AllPlaces.add(new Place(
                "Zagreb, Croatia",
                "Zagreb is made for strolling. Wander through the Upper Town's red-roof and cobblestone glory, peppered with church spires.",
                "https://brightcove04pmdo-a.akamaihd.net/5104226627001/5104226627001_5441113691001_5428833703001-vs.jpg?pubId=5104226627001&videoId=5428833703001",
                "https://flagpedia.net/data/flags/w580/hr.png"));
        AllPlaces.add(new Place(
                "Ljubljana, Slovenia",
                "Slovenia's capital and largest city is one of Europe's greenest and most liveable capitals; it was the European Commission's Green Capital of Europe in 2016.",
                "https://lonelyplanetimages.imgix.net/mastheads/GettyImages-494314513_high.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/si.png"));
        AllPlaces.add(new Place(
                "Sofia, Bulgaria",
                "Bulgaria's pleasingly laid-back capital is often overlooked by visitors heading to the coast or the ski resorts, but they're missing something special.",
                "https://happytowander.com/wp-content/uploads/Sofia-Bulgaria-Happy-to-Wander-0609.jpg",
                "https://flagpedia.net/data/flags/w580/bg.png"));
        AllPlaces.add(new Place(
                "Istanbul, Turkey",
                "This magical meeting place of East and West has more top-drawer attractions than it has minarets (and that's a lot).",
                "http://blog.radissonblu.com/wp-content/uploads/2017/07/Blue-Mosque.jpg",
                "https://flagpedia.net/data/flags/w580/tr.png"));
        AllPlaces.add(new Place(
                "Chisinau, Moldova",
                "Pretty much all roads in Moldova lead to its wine-and-food loving capital.",
                "https://d1z15fh6odiy9s.cloudfront.net/plonk/adfb.../adfba780ee49dd02aa788772e39b7b44497517c80233d3e1cbad1c138f694a02-large.jpg",
                "https://flagpedia.net/data/flags/w580/md.png"));
        AllPlaces.add(new Place(
                "Bucharest, Romania",
                "Romania’s capital sometimes gets a bad rap, but in fact it's dynamic, energetic and lots of fun. Many travellers give the city just a night or two before heading off to Transylvania, but that’s not enough time.",
                "https://www.romania-insider.com/sites/default/files/styles/article_large_image/public/2019-02/bucharest_universitatii_square_-_shutterstock.jpg",
                "https://flagpedia.net/data/flags/w580/ro.png"));
        AllPlaces.add(new Place(
                "Budapest, Hungary",
                "Budapest has something for everyone – from dramatic history and flamboyant architecture to healing thermal waters and a nightlife that is unrivalled in Eastern and Central Europe.",
                "https://brightcove04pmdo-a.akamaihd.net/5104226627001/5104226627001_5515999951001_5214933106001-vs.jpg?pubId=5104226627001&videoId=5214933106001",
                "https://flagpedia.net/data/flags/w580/hu.png"));
        AllPlaces.add(new Place(
                "Rome, Italy",
                "A heady mix of haunting ruins, awe-inspiring art and vibrant street life, Italy’s hot-blooded capital is one of the world’s most romantic and charismatic cities.",
                "https://cdn.citywonders.com/media/17764/rome-city.jpg?anchor=center&mode=crop&width=1200&height=600",
                "https://flagpedia.net/data/flags/w580/it.png"));
        AllPlaces.add(new Place(
                "Bern, Switzerland",
                "Wandering through the picture-postcard, Unesco World Heritage–listed Old Town, with its provincial, laid-back air, it's hard to believe that Bern (Berne in French) is the capital of Switzerland.",
                "https://d2mpqlmtgl1znu.cloudfront.net/AcuCustom/Sitename/DAM/017/Bern_Adobe.jpg",
                "https://flagpedia.net/data/flags/w580/ch.png"));
        AllPlaces.add(new Place(
                "Vienna, Austria",
                "Baroque streetscapes and imperial palaces set the stage for Vienna's artistic and musical masterpieces alongside its coffee-house culture and vibrant epicurean and design scenes.",
                "https://img.theculturetrip.com/x/smart/wp-content/uploads/2019/04/eur-austria-vienna.jpg",
                "https://flagpedia.net/data/flags/w580/at.png"));
        AllPlaces.add(new Place(
                "Bratislava, Slovakia",
                "Slovakia's capital since the country's independence in 1993, Bratislava is a mosaic of illustrious history: a medieval and Gothic old town, baroque palaces commissioned by Hungarian nobles, and the crowning castle, rebuilt to Renaissance finery.",
                "https://s27363.pcdn.co/wp-content/uploads/2018/08/Bratislava-Slovakia-1129x752.jpg.optimal.jpg",
                "https://flagpedia.net/data/flags/w580/sk.png"));
        AllPlaces.add(new Place(
                "Prague, Czech Republic",
                "Prague is the equal of Paris in terms of beauty. Its history goes back a millennium. And the beer? The best in Europe.",
                "https://cdn.thecrazytourist.com/wp-content/uploads/2018/06/ccimage-shutterstock_428804917.jpg",
                "https://flagpedia.net/data/flags/w580/cz.png"));
        AllPlaces.add(new Place(
                "Kiev, Ukraine",
                "Long before Ukraine and Russia existed, the city's inhabitants were already striding up and down the green hills, idling hot afternoons away on the Dnipro River and promenading along Khreshchatyk then a stream, now the main avenue.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/a6469f3993128cbcdde6e2b0a90aa1d3-kyiv.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/ua.png"));
        AllPlaces.add(new Place(
                "Warsaw, Poland",
                "A phoenix arisen from the ashes, Poland's capital impresses with its resilience, respect for history, contemporary style and sheer joie de vivre.",
                "https://www.roughguides.com/wp-content/uploads/2013/04/warsaw-old-town-royal-castle-poland-shutterstock_1171262353.jpg",
                "https://flagpedia.net/data/flags/w580/pl.png"));
        AllPlaces.add(new Place(
                "Minsk, Belarus",
                "Minsk will almost certainly surprise you. The capital of Belarus is, contrary to its dreary reputation, a progressive, modern and clean place.",
                "https://lonelyplanetimages.imgix.net/mastheads/513008517_large.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/by.png"));
        AllPlaces.add(new Place(
                "Vilnius, Lithuania",
                "There is a dreamy quality to Vilnius (vil-nyus), especially in the golden glow of a midsummer evening.",
                "https://content.r9cdn.net/rimg/dimg/09/c0/92c9d67d-city-7110-162f78f1fbc.jpg?crop=true&width=1000&height=600&xhint=2126&yhint=1263",
                "https://flagpedia.net/data/flags/w580/lt.png"));
        AllPlaces.add(new Place(
                "Riga, Latvia",
                "The Gothic spires that dominate Rīga's cityscape might suggest austerity, but it is the flamboyant art nouveau that forms the flesh and the spirit of this vibrant cosmopolitan city, the largest of all three Baltic capitals.",
                "https://www.roadaffair.com/wp-content/uploads/2019/03/house-blackheads-saint-peters-church-riga-latvia-shutterstock_1070326685-1024x683.jpg",
                "https://flagpedia.net/data/flags/w580/lv.png"));
        AllPlaces.add(new Place(
                "Tallinn, Estonia",
                "No longer the plaything of greater powers – Danish, Swedish, Polish, German and Soviet – Tallinn is now a proud European capital with an allure all of its own.",
                "https://www.nomadepicureans.com/wp-content/uploads/2019/05/Tallinn-Walking-Tour-Feature-Image.jpg",
                "https://flagpedia.net/data/flags/w580/ee.png"));
        AllPlaces.add(new Place(
                "Helsinki, Finland",
                "Entwined with the Baltic's bays, inlets and islands, Helsinki's boulevards and backstreets overflow with magnificent architecture, intriguing drinking and dining venues and groundbreaking design.",
                "http://www.hel.fi/wps/wcm/connect/5a20f5c2-6516-40e7-a239-ef6585fdfcfd/englkielisetpalvelutuutiskuva.jpg?MOD=AJPERES&CACHEID=ROOTWORKSPACE-5a20f5c2-6516-40e7-a239-ef6585fdfcfd-mz18k6W",
                "https://flagpedia.net/data/flags/w580/fi.png"));
        AllPlaces.add(new Place(
                "Stockholm, Sweden",
                "Stockholmers call their city 'beauty on water'. But despite the well-preserved historic core, Stockholm is no museum piece: it's modern, dynamic and ever-evolving.",
                "https://a.cdn-hotels.com/gdcs/production151/d1898/abc9d800-c31d-11e8-825c-0242ac110006.jpg",
                "https://flagpedia.net/data/flags/w580/se.png"));
        AllPlaces.add(new Place(
                "Reykjavik, Iceland",
                "The world’s most northerly capital combines colourful buildings, quirky, creative people, eye-popping design, wild nightlife and a capricious soul.",
                "https://www.roughguides.com/wp-content/uploads/2013/01/hallgrimskirkja-cathedral-city-reykjavik-iceland-shutterstock_613997816.jpg",
                "https://flagpedia.net/data/flags/w580/is.png"));
        AllPlaces.add(new Place(
                "Oslo, Norway",
                "Surrounded by mountains and sea, this compact, cultured and fun city has a palpable sense of reinvention.",
                "https://cdn-image.travelandleisure.com/sites/default/files/styles/1600x1000/public/1498156851/opera-house-oslo-norway-OSLO0617.jpg?itok=rcuoL2da",
                "https://flagpedia.net/data/flags/w580/no.png"));
        AllPlaces.add(new Place(
                "Copenhagen, Denmark",
                "Copenhagen is the epitome of Scandi cool. Modernist lamps light New Nordic tables, bridges buzz with cycling commuters and eye-candy locals dive into pristine waterways.",
                "https://lonelyplanetimages.imgix.net/mastheads/GettyImages-126140612_super%20.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/dk.png"));
        AllPlaces.add(new Place(
                "Amsterdam, Netherlands",
                "Golden Age canals lined by tilting gabled buildings are the backdrop for Amsterdam's treasure-packed museums, vintage-filled shops and hyper-creative drinking, dining and design scenes.",
                "http://cdn.cnn.com/cnnnext/dam/assets/190315131613-07-netherlands-happiest-coutries-2018.jpg",
                "https://flagpedia.net/data/flags/w580/nl.png"));
        AllPlaces.add(new Place(
                "Dublin, Ireland",
                "A small capital with a huge reputation, Dublin has a mix of heritage and hedonism that will not disappoint. All you have to do is show up.",
                "https://thesefootballtimes.co/wp-content/uploads/2015/10/Dublin-Ireland.jpg",
                "https://flagpedia.net/data/flags/w580/ie.png"));
        AllPlaces.add(new Place(
                "Brussels, Belgium",
                "Historic yet hip, bureaucratic yet bizarre, self-confident yet unshowy, Brussels is multicultural to its roots.",
                "https://media.timeout.com/images/105237721/image.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/Flag_of_Belgium.svg/2000px-Flag_of_Belgium.svg.png"));
        AllPlaces.add(new Place(
                "Berlin, Germany",
                "Berlin's combo of glamour and grit is bound to mesmerise all those keen to explore its vibrant culture, cutting-edge architecture, fabulous food, intense parties and tangible history.",
                "https://www.berlin-welcomecard.de/sites/default/files/styles/stage_desktop_20x/public/berlin-welcomecard_hero_2880__1.jpg?itok=fgbQwjEO&timestamp=1528123533",
                "https://flagpedia.net/data/flags/ultra/de.png"));
        AllPlaces.add(new Place(
                "Paris, France",
                "Paris' monument-lined boulevards, museums, classical bistros and boutiques are enhanced by a new wave of multimedia galleries, creative wine bars, design shops and tech start-ups.",
                "https://www.fodors.com/wp-content/uploads/2018/10/HERO_UltimateParis_Heroshutterstock_112137761.jpg",
                "https://flagpedia.net/data/flags/ultra/fr.png"));
        AllPlaces.add(new Place(
                "Lisbon, Portugal",
                "Seven cinematic hillsides overlooking the Rio Tejo cradle Lisbon's postcard-perfect panorama of cobbled alleyways, ancient ruins and white-domed cathedrals a captivating scene crafted over centuries.",
                "https://www.lisbonguru.com/wp-content/uploads/2015/11/viewpoints-lisbon.jpg",
                "https://flagpedia.net/data/flags/ultra/pt.png"));
        AllPlaces.add(new Place(
                "Madrid, Spain",
                "Madrid is a beguiling place with an energy that carries one simple message: this city really knows how to live.",
                "https://avisassets.abgemea.com/dam/jcr:afda6cf9-fc35-4c47-940a-baa594ab04ed/Grand_Via_Madrid_Credit_iStock_kasto80.jpg",
                "https://flagpedia.net/data/flags/ultra/es.png"));
        AllPlaces.add(new Place(
                "London, United Kingdom",
                "Immersed in history, London's rich seams of eye-opening antiquity are everywhere.",
                "http://qarva.com/wp-content/gallery/ott-2018/london-background-14.jpeg",
                "https://flagpedia.net/data/flags/ultra/gb.png"));
        AllPlaces.add(new Place(
                "Santorini, Greece",
                "Santorini is the supermodel of the Greek islands, a head-turner whose face is instantly recognisable around the world: multicoloured cliffs soar out of a sea-drowned caldera, topped by drifts of whitewashed buildings.",
                "https://dak95nwic4sny.cloudfront.net/73/santorini-44228168-1550653710-ImageGalleryLightboxLarge.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Flag_of_Greece.svg/2000px-Flag_of_Greece.svg.png"));
        AllPlaces.add(new Place(
                "Sydney, Australia",
                "Sydney, spectacularly draped around its glorious harbour and beaches, has visual wow factor like few other cities. Scratch the surface and it only gets better.",
                "https://www.tourstogo.com.au/things-to-do/wp-content/uploads/2018/12/Sydney-Featured.jpg",
                "https://static.grainger.com/rp/s/is/image/Grainger/5JFT9_AL01"));
        adapter.notifyDataSetChanged();
    }

    public void addEuropeAfrica()
    {
        AllPlaces.clear();
        AllPlaces.add(new Place(
                "Skopje, Macedonia",
                "Skopje has plenty of charm. Its Ottoman- and Byzantine-era sights are focused around the city's delightful Čaršija, bordered by the 15th-century Kameni Most (Stone Bridge) and Tvrdina Kale Fortress – Skopje's guardian since the 5th century.",
                "https://lonelyplanetimages.imgix.net/mastheads/83405857%20.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/mk.png"));
        AllPlaces.add(new Place(
                "Pristina, Kosovo",
                "Pristina is a fast-changing city that feels full of optimism and potential, even if its traffic-clogged streets and mismatched architectural styles don't make it an obviously attractive place.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/e228bf3be784ffff7a338ec8d9167d30-pristina.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/ks.png"));
        AllPlaces.add(new Place(
                "Tirana, Albania",
                "Lively, colourful Tirana is where this tiny nation's hopes and dreams coalesce into a vibrant whirl of traffic, brash consumerism and unfettered fun.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/25e80d94ce0c6dfee0634a2b8598feae-tirana.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/al.png"));
        AllPlaces.add(new Place(
                "Podgorica, Montenegro",
                "Given it’s undergone five name changes, passed through the hands of everyone from the Romans to the Turks to the Austro-Hungarians, and twice been wiped off the map entirely, it’s little wonder that Podgorica (Подгорица) seems permanently gripped by an identity crisis.",
                "https://lonelyplanetimages.imgix.net/mastheads/147031066_medium.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/me.png"));
        AllPlaces.add(new Place(
                "Sarajevo, Bosnia Herzegovina",
                "Ringed by mountains, Sarajevo is a singular city with an enticing East-meets-West vibe all of its own. It was once renowned as a religious melting pot, earning it the epithet 'the Jerusalem of Europe'.",
                "https://s23835.pcdn.co/wp-content/uploads/2018/07/Bosnia-Sarajevo-view-4.jpg",
                "https://flagpedia.net/data/flags/w580/ba.png"));
        AllPlaces.add(new Place(
                "Belgrade, Serbia",
                "Outspoken, adventurous, proud and audacious: Belgrade ('White City') is by no means a 'pretty' capital, but its gritty exuberance makes it one of Europe's most happening cities.",
                "https://media.architecturaldigest.com/photos/5d37554e47a77b000872bbf4/16:9/w_1280,c_limit/GettyImages-847708760.jpg",
                "https://flagpedia.net/data/flags/w580/rs.png"));
        AllPlaces.add(new Place(
                "Zagreb, Croatia",
                "Zagreb is made for strolling. Wander through the Upper Town's red-roof and cobblestone glory, peppered with church spires.",
                "https://brightcove04pmdo-a.akamaihd.net/5104226627001/5104226627001_5441113691001_5428833703001-vs.jpg?pubId=5104226627001&videoId=5428833703001",
                "https://flagpedia.net/data/flags/w580/hr.png"));
        AllPlaces.add(new Place(
                "Ljubljana, Slovenia",
                "Slovenia's capital and largest city is one of Europe's greenest and most liveable capitals; it was the European Commission's Green Capital of Europe in 2016.",
                "https://lonelyplanetimages.imgix.net/mastheads/GettyImages-494314513_high.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/si.png"));
        AllPlaces.add(new Place(
                "Sofia, Bulgaria",
                "Bulgaria's pleasingly laid-back capital is often overlooked by visitors heading to the coast or the ski resorts, but they're missing something special.",
                "https://happytowander.com/wp-content/uploads/Sofia-Bulgaria-Happy-to-Wander-0609.jpg",
                "https://flagpedia.net/data/flags/w580/bg.png"));
        AllPlaces.add(new Place(
                "Istanbul, Turkey",
                "This magical meeting place of East and West has more top-drawer attractions than it has minarets (and that's a lot).",
                "http://blog.radissonblu.com/wp-content/uploads/2017/07/Blue-Mosque.jpg",
                "https://flagpedia.net/data/flags/w580/tr.png"));
        AllPlaces.add(new Place(
                "Chisinau, Moldova",
                "Pretty much all roads in Moldova lead to its wine-and-food loving capital.",
                "https://d1z15fh6odiy9s.cloudfront.net/plonk/adfb.../adfba780ee49dd02aa788772e39b7b44497517c80233d3e1cbad1c138f694a02-large.jpg",
                "https://flagpedia.net/data/flags/w580/md.png"));
        AllPlaces.add(new Place(
                "Bucharest, Romania",
                "Romania’s capital sometimes gets a bad rap, but in fact it's dynamic, energetic and lots of fun. Many travellers give the city just a night or two before heading off to Transylvania, but that’s not enough time.",
                "https://www.romania-insider.com/sites/default/files/styles/article_large_image/public/2019-02/bucharest_universitatii_square_-_shutterstock.jpg",
                "https://flagpedia.net/data/flags/w580/ro.png"));
        AllPlaces.add(new Place(
                "Budapest, Hungary",
                "Budapest has something for everyone – from dramatic history and flamboyant architecture to healing thermal waters and a nightlife that is unrivalled in Eastern and Central Europe.",
                "https://brightcove04pmdo-a.akamaihd.net/5104226627001/5104226627001_5515999951001_5214933106001-vs.jpg?pubId=5104226627001&videoId=5214933106001",
                "https://flagpedia.net/data/flags/w580/hu.png"));
        AllPlaces.add(new Place(
                "Rome, Italy",
                "A heady mix of haunting ruins, awe-inspiring art and vibrant street life, Italy’s hot-blooded capital is one of the world’s most romantic and charismatic cities.",
                "https://cdn.citywonders.com/media/17764/rome-city.jpg?anchor=center&mode=crop&width=1200&height=600",
                "https://flagpedia.net/data/flags/w580/it.png"));
        AllPlaces.add(new Place(
                "Bern, Switzerland",
                "Wandering through the picture-postcard, Unesco World Heritage–listed Old Town, with its provincial, laid-back air, it's hard to believe that Bern (Berne in French) is the capital of Switzerland.",
                "https://d2mpqlmtgl1znu.cloudfront.net/AcuCustom/Sitename/DAM/017/Bern_Adobe.jpg",
                "https://flagpedia.net/data/flags/w580/ch.png"));
        AllPlaces.add(new Place(
                "Vienna, Austria",
                "Baroque streetscapes and imperial palaces set the stage for Vienna's artistic and musical masterpieces alongside its coffee-house culture and vibrant epicurean and design scenes.",
                "https://img.theculturetrip.com/x/smart/wp-content/uploads/2019/04/eur-austria-vienna.jpg",
                "https://flagpedia.net/data/flags/w580/at.png"));
        AllPlaces.add(new Place(
                "Bratislava, Slovakia",
                "Slovakia's capital since the country's independence in 1993, Bratislava is a mosaic of illustrious history: a medieval and Gothic old town, baroque palaces commissioned by Hungarian nobles, and the crowning castle, rebuilt to Renaissance finery.",
                "https://s27363.pcdn.co/wp-content/uploads/2018/08/Bratislava-Slovakia-1129x752.jpg.optimal.jpg",
                "https://flagpedia.net/data/flags/w580/sk.png"));
        AllPlaces.add(new Place(
                "Prague, Czech Republic",
                "Prague is the equal of Paris in terms of beauty. Its history goes back a millennium. And the beer? The best in Europe.",
                "https://cdn.thecrazytourist.com/wp-content/uploads/2018/06/ccimage-shutterstock_428804917.jpg",
                "https://flagpedia.net/data/flags/w580/cz.png"));
        AllPlaces.add(new Place(
                "Kiev, Ukraine",
                "Long before Ukraine and Russia existed, the city's inhabitants were already striding up and down the green hills, idling hot afternoons away on the Dnipro River and promenading along Khreshchatyk then a stream, now the main avenue.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/a6469f3993128cbcdde6e2b0a90aa1d3-kyiv.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/ua.png"));
        AllPlaces.add(new Place(
                "Warsaw, Poland",
                "A phoenix arisen from the ashes, Poland's capital impresses with its resilience, respect for history, contemporary style and sheer joie de vivre.",
                "https://www.roughguides.com/wp-content/uploads/2013/04/warsaw-old-town-royal-castle-poland-shutterstock_1171262353.jpg",
                "https://flagpedia.net/data/flags/w580/pl.png"));
        AllPlaces.add(new Place(
                "Minsk, Belarus",
                "Minsk will almost certainly surprise you. The capital of Belarus is, contrary to its dreary reputation, a progressive, modern and clean place.",
                "https://lonelyplanetimages.imgix.net/mastheads/513008517_large.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/by.png"));
        AllPlaces.add(new Place(
                "Vilnius, Lithuania",
                "There is a dreamy quality to Vilnius (vil-nyus), especially in the golden glow of a midsummer evening.",
                "https://content.r9cdn.net/rimg/dimg/09/c0/92c9d67d-city-7110-162f78f1fbc.jpg?crop=true&width=1000&height=600&xhint=2126&yhint=1263",
                "https://flagpedia.net/data/flags/w580/lt.png"));
        AllPlaces.add(new Place(
                "Riga, Latvia",
                "The Gothic spires that dominate Rīga's cityscape might suggest austerity, but it is the flamboyant art nouveau that forms the flesh and the spirit of this vibrant cosmopolitan city, the largest of all three Baltic capitals.",
                "https://www.roadaffair.com/wp-content/uploads/2019/03/house-blackheads-saint-peters-church-riga-latvia-shutterstock_1070326685-1024x683.jpg",
                "https://flagpedia.net/data/flags/w580/lv.png"));
        AllPlaces.add(new Place(
                "Tallinn, Estonia",
                "No longer the plaything of greater powers – Danish, Swedish, Polish, German and Soviet – Tallinn is now a proud European capital with an allure all of its own.",
                "https://www.nomadepicureans.com/wp-content/uploads/2019/05/Tallinn-Walking-Tour-Feature-Image.jpg",
                "https://flagpedia.net/data/flags/w580/ee.png"));
        AllPlaces.add(new Place(
                "Helsinki, Finland",
                "Entwined with the Baltic's bays, inlets and islands, Helsinki's boulevards and backstreets overflow with magnificent architecture, intriguing drinking and dining venues and groundbreaking design.",
                "http://www.hel.fi/wps/wcm/connect/5a20f5c2-6516-40e7-a239-ef6585fdfcfd/englkielisetpalvelutuutiskuva.jpg?MOD=AJPERES&CACHEID=ROOTWORKSPACE-5a20f5c2-6516-40e7-a239-ef6585fdfcfd-mz18k6W",
                "https://flagpedia.net/data/flags/w580/fi.png"));
        AllPlaces.add(new Place(
                "Stockholm, Sweden",
                "Stockholmers call their city 'beauty on water'. But despite the well-preserved historic core, Stockholm is no museum piece: it's modern, dynamic and ever-evolving.",
                "https://a.cdn-hotels.com/gdcs/production151/d1898/abc9d800-c31d-11e8-825c-0242ac110006.jpg",
                "https://flagpedia.net/data/flags/w580/se.png"));
        AllPlaces.add(new Place(
                "Reykjavik, Iceland",
                "The world’s most northerly capital combines colourful buildings, quirky, creative people, eye-popping design, wild nightlife and a capricious soul.",
                "https://www.roughguides.com/wp-content/uploads/2013/01/hallgrimskirkja-cathedral-city-reykjavik-iceland-shutterstock_613997816.jpg",
                "https://flagpedia.net/data/flags/w580/is.png"));
        AllPlaces.add(new Place(
                "Oslo, Norway",
                "Surrounded by mountains and sea, this compact, cultured and fun city has a palpable sense of reinvention.",
                "https://cdn-image.travelandleisure.com/sites/default/files/styles/1600x1000/public/1498156851/opera-house-oslo-norway-OSLO0617.jpg?itok=rcuoL2da",
                "https://flagpedia.net/data/flags/w580/no.png"));
        AllPlaces.add(new Place(
                "Copenhagen, Denmark",
                "Copenhagen is the epitome of Scandi cool. Modernist lamps light New Nordic tables, bridges buzz with cycling commuters and eye-candy locals dive into pristine waterways.",
                "https://lonelyplanetimages.imgix.net/mastheads/GettyImages-126140612_super%20.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/dk.png"));
        AllPlaces.add(new Place(
                "Amsterdam, Netherlands",
                "Golden Age canals lined by tilting gabled buildings are the backdrop for Amsterdam's treasure-packed museums, vintage-filled shops and hyper-creative drinking, dining and design scenes.",
                "http://cdn.cnn.com/cnnnext/dam/assets/190315131613-07-netherlands-happiest-coutries-2018.jpg",
                "https://flagpedia.net/data/flags/w580/nl.png"));
        AllPlaces.add(new Place(
                "Dublin, Ireland",
                "A small capital with a huge reputation, Dublin has a mix of heritage and hedonism that will not disappoint. All you have to do is show up.",
                "https://thesefootballtimes.co/wp-content/uploads/2015/10/Dublin-Ireland.jpg",
                "https://flagpedia.net/data/flags/w580/ie.png"));
        AllPlaces.add(new Place(
                "Brussels, Belgium",
                "Historic yet hip, bureaucratic yet bizarre, self-confident yet unshowy, Brussels is multicultural to its roots.",
                "https://media.timeout.com/images/105237721/image.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/Flag_of_Belgium.svg/2000px-Flag_of_Belgium.svg.png"));
        AllPlaces.add(new Place(
                "Berlin, Germany",
                "Berlin's combo of glamour and grit is bound to mesmerise all those keen to explore its vibrant culture, cutting-edge architecture, fabulous food, intense parties and tangible history.",
                "https://www.berlin-welcomecard.de/sites/default/files/styles/stage_desktop_20x/public/berlin-welcomecard_hero_2880__1.jpg?itok=fgbQwjEO&timestamp=1528123533",
                "https://flagpedia.net/data/flags/ultra/de.png"));
        AllPlaces.add(new Place(
                "Paris, France",
                "Paris' monument-lined boulevards, museums, classical bistros and boutiques are enhanced by a new wave of multimedia galleries, creative wine bars, design shops and tech start-ups.",
                "https://www.fodors.com/wp-content/uploads/2018/10/HERO_UltimateParis_Heroshutterstock_112137761.jpg",
                "https://flagpedia.net/data/flags/ultra/fr.png"));
        AllPlaces.add(new Place(
                "Lisbon, Portugal",
                "Seven cinematic hillsides overlooking the Rio Tejo cradle Lisbon's postcard-perfect panorama of cobbled alleyways, ancient ruins and white-domed cathedrals a captivating scene crafted over centuries.",
                "https://www.lisbonguru.com/wp-content/uploads/2015/11/viewpoints-lisbon.jpg",
                "https://flagpedia.net/data/flags/ultra/pt.png"));
        AllPlaces.add(new Place(
                "Madrid, Spain",
                "Madrid is a beguiling place with an energy that carries one simple message: this city really knows how to live.",
                "https://avisassets.abgemea.com/dam/jcr:afda6cf9-fc35-4c47-940a-baa594ab04ed/Grand_Via_Madrid_Credit_iStock_kasto80.jpg",
                "https://flagpedia.net/data/flags/ultra/es.png"));
        AllPlaces.add(new Place(
                "London, United Kingdom",
                "Immersed in history, London's rich seams of eye-opening antiquity are everywhere.",
                "http://qarva.com/wp-content/gallery/ott-2018/london-background-14.jpeg",
                "https://flagpedia.net/data/flags/ultra/gb.png"));
        AllPlaces.add(new Place(
                "Santorini, Greece",
                "Santorini is the supermodel of the Greek islands, a head-turner whose face is instantly recognisable around the world: multicoloured cliffs soar out of a sea-drowned caldera, topped by drifts of whitewashed buildings.",
                "https://dak95nwic4sny.cloudfront.net/73/santorini-44228168-1550653710-ImageGalleryLightboxLarge.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Flag_of_Greece.svg/2000px-Flag_of_Greece.svg.png"));
        AllPlaces.add(new Place(
                "Nairobi, Kenya",
                "East Africa's most cosmopolitan city, Nairobi is Kenya's beating heart, an exciting, maddening concrete jungle that jarringly counterpoints the untrammelled natural beauty to be found elsewhere in the country.",
                "https://dak95nwic4sny.cloudfront.net/73/kenya-beach-and-safari-43962495-1554105066-ImageGalleryLightboxLarge.jpg",
                "https://flagpedia.net/data/flags/w580/ke.png"));
        adapter.notifyDataSetChanged();
    }

    public void addEuropeSouthAmerica()
    {
        AllPlaces.clear();
        AllPlaces.add(new Place(
                "Skopje, Macedonia",
                "Skopje has plenty of charm. Its Ottoman- and Byzantine-era sights are focused around the city's delightful Čaršija, bordered by the 15th-century Kameni Most (Stone Bridge) and Tvrdina Kale Fortress – Skopje's guardian since the 5th century.",
                "https://lonelyplanetimages.imgix.net/mastheads/83405857%20.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/mk.png"));
        AllPlaces.add(new Place(
                "Pristina, Kosovo",
                "Pristina is a fast-changing city that feels full of optimism and potential, even if its traffic-clogged streets and mismatched architectural styles don't make it an obviously attractive place.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/e228bf3be784ffff7a338ec8d9167d30-pristina.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/ks.png"));
        AllPlaces.add(new Place(
                "Tirana, Albania",
                "Lively, colourful Tirana is where this tiny nation's hopes and dreams coalesce into a vibrant whirl of traffic, brash consumerism and unfettered fun.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/25e80d94ce0c6dfee0634a2b8598feae-tirana.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/al.png"));
        AllPlaces.add(new Place(
                "Podgorica, Montenegro",
                "Given it’s undergone five name changes, passed through the hands of everyone from the Romans to the Turks to the Austro-Hungarians, and twice been wiped off the map entirely, it’s little wonder that Podgorica (Подгорица) seems permanently gripped by an identity crisis.",
                "https://lonelyplanetimages.imgix.net/mastheads/147031066_medium.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/me.png"));
        AllPlaces.add(new Place(
                "Sarajevo, Bosnia Herzegovina",
                "Ringed by mountains, Sarajevo is a singular city with an enticing East-meets-West vibe all of its own. It was once renowned as a religious melting pot, earning it the epithet 'the Jerusalem of Europe'.",
                "https://s23835.pcdn.co/wp-content/uploads/2018/07/Bosnia-Sarajevo-view-4.jpg",
                "https://flagpedia.net/data/flags/w580/ba.png"));
        AllPlaces.add(new Place(
                "Belgrade, Serbia",
                "Outspoken, adventurous, proud and audacious: Belgrade ('White City') is by no means a 'pretty' capital, but its gritty exuberance makes it one of Europe's most happening cities.",
                "https://media.architecturaldigest.com/photos/5d37554e47a77b000872bbf4/16:9/w_1280,c_limit/GettyImages-847708760.jpg",
                "https://flagpedia.net/data/flags/w580/rs.png"));
        AllPlaces.add(new Place(
                "Zagreb, Croatia",
                "Zagreb is made for strolling. Wander through the Upper Town's red-roof and cobblestone glory, peppered with church spires.",
                "https://brightcove04pmdo-a.akamaihd.net/5104226627001/5104226627001_5441113691001_5428833703001-vs.jpg?pubId=5104226627001&videoId=5428833703001",
                "https://flagpedia.net/data/flags/w580/hr.png"));
        AllPlaces.add(new Place(
                "Ljubljana, Slovenia",
                "Slovenia's capital and largest city is one of Europe's greenest and most liveable capitals; it was the European Commission's Green Capital of Europe in 2016.",
                "https://lonelyplanetimages.imgix.net/mastheads/GettyImages-494314513_high.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/si.png"));
        AllPlaces.add(new Place(
                "Sofia, Bulgaria",
                "Bulgaria's pleasingly laid-back capital is often overlooked by visitors heading to the coast or the ski resorts, but they're missing something special.",
                "https://happytowander.com/wp-content/uploads/Sofia-Bulgaria-Happy-to-Wander-0609.jpg",
                "https://flagpedia.net/data/flags/w580/bg.png"));
        AllPlaces.add(new Place(
                "Istanbul, Turkey",
                "This magical meeting place of East and West has more top-drawer attractions than it has minarets (and that's a lot).",
                "http://blog.radissonblu.com/wp-content/uploads/2017/07/Blue-Mosque.jpg",
                "https://flagpedia.net/data/flags/w580/tr.png"));
        AllPlaces.add(new Place(
                "Chisinau, Moldova",
                "Pretty much all roads in Moldova lead to its wine-and-food loving capital.",
                "https://d1z15fh6odiy9s.cloudfront.net/plonk/adfb.../adfba780ee49dd02aa788772e39b7b44497517c80233d3e1cbad1c138f694a02-large.jpg",
                "https://flagpedia.net/data/flags/w580/md.png"));
        AllPlaces.add(new Place(
                "Bucharest, Romania",
                "Romania’s capital sometimes gets a bad rap, but in fact it's dynamic, energetic and lots of fun. Many travellers give the city just a night or two before heading off to Transylvania, but that’s not enough time.",
                "https://www.romania-insider.com/sites/default/files/styles/article_large_image/public/2019-02/bucharest_universitatii_square_-_shutterstock.jpg",
                "https://flagpedia.net/data/flags/w580/ro.png"));
        AllPlaces.add(new Place(
                "Budapest, Hungary",
                "Budapest has something for everyone – from dramatic history and flamboyant architecture to healing thermal waters and a nightlife that is unrivalled in Eastern and Central Europe.",
                "https://brightcove04pmdo-a.akamaihd.net/5104226627001/5104226627001_5515999951001_5214933106001-vs.jpg?pubId=5104226627001&videoId=5214933106001",
                "https://flagpedia.net/data/flags/w580/hu.png"));
        AllPlaces.add(new Place(
                "Rome, Italy",
                "A heady mix of haunting ruins, awe-inspiring art and vibrant street life, Italy’s hot-blooded capital is one of the world’s most romantic and charismatic cities.",
                "https://cdn.citywonders.com/media/17764/rome-city.jpg?anchor=center&mode=crop&width=1200&height=600",
                "https://flagpedia.net/data/flags/w580/it.png"));
        AllPlaces.add(new Place(
                "Bern, Switzerland",
                "Wandering through the picture-postcard, Unesco World Heritage–listed Old Town, with its provincial, laid-back air, it's hard to believe that Bern (Berne in French) is the capital of Switzerland.",
                "https://d2mpqlmtgl1znu.cloudfront.net/AcuCustom/Sitename/DAM/017/Bern_Adobe.jpg",
                "https://flagpedia.net/data/flags/w580/ch.png"));
        AllPlaces.add(new Place(
                "Vienna, Austria",
                "Baroque streetscapes and imperial palaces set the stage for Vienna's artistic and musical masterpieces alongside its coffee-house culture and vibrant epicurean and design scenes.",
                "https://img.theculturetrip.com/x/smart/wp-content/uploads/2019/04/eur-austria-vienna.jpg",
                "https://flagpedia.net/data/flags/w580/at.png"));
        AllPlaces.add(new Place(
                "Bratislava, Slovakia",
                "Slovakia's capital since the country's independence in 1993, Bratislava is a mosaic of illustrious history: a medieval and Gothic old town, baroque palaces commissioned by Hungarian nobles, and the crowning castle, rebuilt to Renaissance finery.",
                "https://s27363.pcdn.co/wp-content/uploads/2018/08/Bratislava-Slovakia-1129x752.jpg.optimal.jpg",
                "https://flagpedia.net/data/flags/w580/sk.png"));
        AllPlaces.add(new Place(
                "Prague, Czech Republic",
                "Prague is the equal of Paris in terms of beauty. Its history goes back a millennium. And the beer? The best in Europe.",
                "https://cdn.thecrazytourist.com/wp-content/uploads/2018/06/ccimage-shutterstock_428804917.jpg",
                "https://flagpedia.net/data/flags/w580/cz.png"));
        AllPlaces.add(new Place(
                "Kiev, Ukraine",
                "Long before Ukraine and Russia existed, the city's inhabitants were already striding up and down the green hills, idling hot afternoons away on the Dnipro River and promenading along Khreshchatyk then a stream, now the main avenue.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/a6469f3993128cbcdde6e2b0a90aa1d3-kyiv.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/ua.png"));
        AllPlaces.add(new Place(
                "Warsaw, Poland",
                "A phoenix arisen from the ashes, Poland's capital impresses with its resilience, respect for history, contemporary style and sheer joie de vivre.",
                "https://www.roughguides.com/wp-content/uploads/2013/04/warsaw-old-town-royal-castle-poland-shutterstock_1171262353.jpg",
                "https://flagpedia.net/data/flags/w580/pl.png"));
        AllPlaces.add(new Place(
                "Minsk, Belarus",
                "Minsk will almost certainly surprise you. The capital of Belarus is, contrary to its dreary reputation, a progressive, modern and clean place.",
                "https://lonelyplanetimages.imgix.net/mastheads/513008517_large.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/by.png"));
        AllPlaces.add(new Place(
                "Vilnius, Lithuania",
                "There is a dreamy quality to Vilnius (vil-nyus), especially in the golden glow of a midsummer evening.",
                "https://content.r9cdn.net/rimg/dimg/09/c0/92c9d67d-city-7110-162f78f1fbc.jpg?crop=true&width=1000&height=600&xhint=2126&yhint=1263",
                "https://flagpedia.net/data/flags/w580/lt.png"));
        AllPlaces.add(new Place(
                "Riga, Latvia",
                "The Gothic spires that dominate Rīga's cityscape might suggest austerity, but it is the flamboyant art nouveau that forms the flesh and the spirit of this vibrant cosmopolitan city, the largest of all three Baltic capitals.",
                "https://www.roadaffair.com/wp-content/uploads/2019/03/house-blackheads-saint-peters-church-riga-latvia-shutterstock_1070326685-1024x683.jpg",
                "https://flagpedia.net/data/flags/w580/lv.png"));
        AllPlaces.add(new Place(
                "Tallinn, Estonia",
                "No longer the plaything of greater powers – Danish, Swedish, Polish, German and Soviet – Tallinn is now a proud European capital with an allure all of its own.",
                "https://www.nomadepicureans.com/wp-content/uploads/2019/05/Tallinn-Walking-Tour-Feature-Image.jpg",
                "https://flagpedia.net/data/flags/w580/ee.png"));
        AllPlaces.add(new Place(
                "Helsinki, Finland",
                "Entwined with the Baltic's bays, inlets and islands, Helsinki's boulevards and backstreets overflow with magnificent architecture, intriguing drinking and dining venues and groundbreaking design.",
                "http://www.hel.fi/wps/wcm/connect/5a20f5c2-6516-40e7-a239-ef6585fdfcfd/englkielisetpalvelutuutiskuva.jpg?MOD=AJPERES&CACHEID=ROOTWORKSPACE-5a20f5c2-6516-40e7-a239-ef6585fdfcfd-mz18k6W",
                "https://flagpedia.net/data/flags/w580/fi.png"));
        AllPlaces.add(new Place(
                "Stockholm, Sweden",
                "Stockholmers call their city 'beauty on water'. But despite the well-preserved historic core, Stockholm is no museum piece: it's modern, dynamic and ever-evolving.",
                "https://a.cdn-hotels.com/gdcs/production151/d1898/abc9d800-c31d-11e8-825c-0242ac110006.jpg",
                "https://flagpedia.net/data/flags/w580/se.png"));
        AllPlaces.add(new Place(
                "Reykjavik, Iceland",
                "The world’s most northerly capital combines colourful buildings, quirky, creative people, eye-popping design, wild nightlife and a capricious soul.",
                "https://www.roughguides.com/wp-content/uploads/2013/01/hallgrimskirkja-cathedral-city-reykjavik-iceland-shutterstock_613997816.jpg",
                "https://flagpedia.net/data/flags/w580/is.png"));
        AllPlaces.add(new Place(
                "Oslo, Norway",
                "Surrounded by mountains and sea, this compact, cultured and fun city has a palpable sense of reinvention.",
                "https://cdn-image.travelandleisure.com/sites/default/files/styles/1600x1000/public/1498156851/opera-house-oslo-norway-OSLO0617.jpg?itok=rcuoL2da",
                "https://flagpedia.net/data/flags/w580/no.png"));
        AllPlaces.add(new Place(
                "Copenhagen, Denmark",
                "Copenhagen is the epitome of Scandi cool. Modernist lamps light New Nordic tables, bridges buzz with cycling commuters and eye-candy locals dive into pristine waterways.",
                "https://lonelyplanetimages.imgix.net/mastheads/GettyImages-126140612_super%20.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/dk.png"));
        AllPlaces.add(new Place(
                "Amsterdam, Netherlands",
                "Golden Age canals lined by tilting gabled buildings are the backdrop for Amsterdam's treasure-packed museums, vintage-filled shops and hyper-creative drinking, dining and design scenes.",
                "http://cdn.cnn.com/cnnnext/dam/assets/190315131613-07-netherlands-happiest-coutries-2018.jpg",
                "https://flagpedia.net/data/flags/w580/nl.png"));
        AllPlaces.add(new Place(
                "Dublin, Ireland",
                "A small capital with a huge reputation, Dublin has a mix of heritage and hedonism that will not disappoint. All you have to do is show up.",
                "https://thesefootballtimes.co/wp-content/uploads/2015/10/Dublin-Ireland.jpg",
                "https://flagpedia.net/data/flags/w580/ie.png"));
        AllPlaces.add(new Place(
                "Brussels, Belgium",
                "Historic yet hip, bureaucratic yet bizarre, self-confident yet unshowy, Brussels is multicultural to its roots.",
                "https://media.timeout.com/images/105237721/image.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/Flag_of_Belgium.svg/2000px-Flag_of_Belgium.svg.png"));
        AllPlaces.add(new Place(
                "Berlin, Germany",
                "Berlin's combo of glamour and grit is bound to mesmerise all those keen to explore its vibrant culture, cutting-edge architecture, fabulous food, intense parties and tangible history.",
                "https://www.berlin-welcomecard.de/sites/default/files/styles/stage_desktop_20x/public/berlin-welcomecard_hero_2880__1.jpg?itok=fgbQwjEO&timestamp=1528123533",
                "https://flagpedia.net/data/flags/ultra/de.png"));
        AllPlaces.add(new Place(
                "Paris, France",
                "Paris' monument-lined boulevards, museums, classical bistros and boutiques are enhanced by a new wave of multimedia galleries, creative wine bars, design shops and tech start-ups.",
                "https://www.fodors.com/wp-content/uploads/2018/10/HERO_UltimateParis_Heroshutterstock_112137761.jpg",
                "https://flagpedia.net/data/flags/ultra/fr.png"));
        AllPlaces.add(new Place(
                "Lisbon, Portugal",
                "Seven cinematic hillsides overlooking the Rio Tejo cradle Lisbon's postcard-perfect panorama of cobbled alleyways, ancient ruins and white-domed cathedrals a captivating scene crafted over centuries.",
                "https://www.lisbonguru.com/wp-content/uploads/2015/11/viewpoints-lisbon.jpg",
                "https://flagpedia.net/data/flags/ultra/pt.png"));
        AllPlaces.add(new Place(
                "Madrid, Spain",
                "Madrid is a beguiling place with an energy that carries one simple message: this city really knows how to live.",
                "https://avisassets.abgemea.com/dam/jcr:afda6cf9-fc35-4c47-940a-baa594ab04ed/Grand_Via_Madrid_Credit_iStock_kasto80.jpg",
                "https://flagpedia.net/data/flags/ultra/es.png"));
        AllPlaces.add(new Place(
                "London, United Kingdom",
                "Immersed in history, London's rich seams of eye-opening antiquity are everywhere.",
                "http://qarva.com/wp-content/gallery/ott-2018/london-background-14.jpeg",
                "https://flagpedia.net/data/flags/ultra/gb.png"));
        AllPlaces.add(new Place(
                "Santorini, Greece",
                "Santorini is the supermodel of the Greek islands, a head-turner whose face is instantly recognisable around the world: multicoloured cliffs soar out of a sea-drowned caldera, topped by drifts of whitewashed buildings.",
                "https://dak95nwic4sny.cloudfront.net/73/santorini-44228168-1550653710-ImageGalleryLightboxLarge.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Flag_of_Greece.svg/2000px-Flag_of_Greece.svg.png"));
        AllPlaces.add(new Place(
                "Buenos Aires, Argentina",
                "Beautiful, defiant and intense, Argentina seduces with its streetside tango, wafting grills, fútbol (soccer), gaucho culture and the mighty Andes. It's one formidable cocktail of wanderlust.",
                "https://d1bvpoagx8hqbg.cloudfront.net/originals/experience-buenos-aires-argentina-evelyn-4f019adee7f47b76494b404b3553e354.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1a/Flag_of_Argentina.svg/2000px-Flag_of_Argentina.svg.png"));
        adapter.notifyDataSetChanged();
    }

    public void addEuropeNorthAmerica()
    {
        AllPlaces.clear();
        AllPlaces.add(new Place(
                "Skopje, Macedonia",
                "Skopje has plenty of charm. Its Ottoman- and Byzantine-era sights are focused around the city's delightful Čaršija, bordered by the 15th-century Kameni Most (Stone Bridge) and Tvrdina Kale Fortress – Skopje's guardian since the 5th century.",
                "https://lonelyplanetimages.imgix.net/mastheads/83405857%20.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/mk.png"));
        AllPlaces.add(new Place(
                "Pristina, Kosovo",
                "Pristina is a fast-changing city that feels full of optimism and potential, even if its traffic-clogged streets and mismatched architectural styles don't make it an obviously attractive place.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/e228bf3be784ffff7a338ec8d9167d30-pristina.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/ks.png"));
        AllPlaces.add(new Place(
                "Tirana, Albania",
                "Lively, colourful Tirana is where this tiny nation's hopes and dreams coalesce into a vibrant whirl of traffic, brash consumerism and unfettered fun.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/25e80d94ce0c6dfee0634a2b8598feae-tirana.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/al.png"));
        AllPlaces.add(new Place(
                "Podgorica, Montenegro",
                "Given it’s undergone five name changes, passed through the hands of everyone from the Romans to the Turks to the Austro-Hungarians, and twice been wiped off the map entirely, it’s little wonder that Podgorica (Подгорица) seems permanently gripped by an identity crisis.",
                "https://lonelyplanetimages.imgix.net/mastheads/147031066_medium.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/me.png"));
        AllPlaces.add(new Place(
                "Sarajevo, Bosnia Herzegovina",
                "Ringed by mountains, Sarajevo is a singular city with an enticing East-meets-West vibe all of its own. It was once renowned as a religious melting pot, earning it the epithet 'the Jerusalem of Europe'.",
                "https://s23835.pcdn.co/wp-content/uploads/2018/07/Bosnia-Sarajevo-view-4.jpg",
                "https://flagpedia.net/data/flags/w580/ba.png"));
        AllPlaces.add(new Place(
                "Belgrade, Serbia",
                "Outspoken, adventurous, proud and audacious: Belgrade ('White City') is by no means a 'pretty' capital, but its gritty exuberance makes it one of Europe's most happening cities.",
                "https://media.architecturaldigest.com/photos/5d37554e47a77b000872bbf4/16:9/w_1280,c_limit/GettyImages-847708760.jpg",
                "https://flagpedia.net/data/flags/w580/rs.png"));
        AllPlaces.add(new Place(
                "Zagreb, Croatia",
                "Zagreb is made for strolling. Wander through the Upper Town's red-roof and cobblestone glory, peppered with church spires.",
                "https://brightcove04pmdo-a.akamaihd.net/5104226627001/5104226627001_5441113691001_5428833703001-vs.jpg?pubId=5104226627001&videoId=5428833703001",
                "https://flagpedia.net/data/flags/w580/hr.png"));
        AllPlaces.add(new Place(
                "Ljubljana, Slovenia",
                "Slovenia's capital and largest city is one of Europe's greenest and most liveable capitals; it was the European Commission's Green Capital of Europe in 2016.",
                "https://lonelyplanetimages.imgix.net/mastheads/GettyImages-494314513_high.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/si.png"));
        AllPlaces.add(new Place(
                "Sofia, Bulgaria",
                "Bulgaria's pleasingly laid-back capital is often overlooked by visitors heading to the coast or the ski resorts, but they're missing something special.",
                "https://happytowander.com/wp-content/uploads/Sofia-Bulgaria-Happy-to-Wander-0609.jpg",
                "https://flagpedia.net/data/flags/w580/bg.png"));
        AllPlaces.add(new Place(
                "Istanbul, Turkey",
                "This magical meeting place of East and West has more top-drawer attractions than it has minarets (and that's a lot).",
                "http://blog.radissonblu.com/wp-content/uploads/2017/07/Blue-Mosque.jpg",
                "https://flagpedia.net/data/flags/w580/tr.png"));
        AllPlaces.add(new Place(
                "Chisinau, Moldova",
                "Pretty much all roads in Moldova lead to its wine-and-food loving capital.",
                "https://d1z15fh6odiy9s.cloudfront.net/plonk/adfb.../adfba780ee49dd02aa788772e39b7b44497517c80233d3e1cbad1c138f694a02-large.jpg",
                "https://flagpedia.net/data/flags/w580/md.png"));
        AllPlaces.add(new Place(
                "Bucharest, Romania",
                "Romania’s capital sometimes gets a bad rap, but in fact it's dynamic, energetic and lots of fun. Many travellers give the city just a night or two before heading off to Transylvania, but that’s not enough time.",
                "https://www.romania-insider.com/sites/default/files/styles/article_large_image/public/2019-02/bucharest_universitatii_square_-_shutterstock.jpg",
                "https://flagpedia.net/data/flags/w580/ro.png"));
        AllPlaces.add(new Place(
                "Budapest, Hungary",
                "Budapest has something for everyone – from dramatic history and flamboyant architecture to healing thermal waters and a nightlife that is unrivalled in Eastern and Central Europe.",
                "https://brightcove04pmdo-a.akamaihd.net/5104226627001/5104226627001_5515999951001_5214933106001-vs.jpg?pubId=5104226627001&videoId=5214933106001",
                "https://flagpedia.net/data/flags/w580/hu.png"));
        AllPlaces.add(new Place(
                "Rome, Italy",
                "A heady mix of haunting ruins, awe-inspiring art and vibrant street life, Italy’s hot-blooded capital is one of the world’s most romantic and charismatic cities.",
                "https://cdn.citywonders.com/media/17764/rome-city.jpg?anchor=center&mode=crop&width=1200&height=600",
                "https://flagpedia.net/data/flags/w580/it.png"));
        AllPlaces.add(new Place(
                "Bern, Switzerland",
                "Wandering through the picture-postcard, Unesco World Heritage–listed Old Town, with its provincial, laid-back air, it's hard to believe that Bern (Berne in French) is the capital of Switzerland.",
                "https://d2mpqlmtgl1znu.cloudfront.net/AcuCustom/Sitename/DAM/017/Bern_Adobe.jpg",
                "https://flagpedia.net/data/flags/w580/ch.png"));
        AllPlaces.add(new Place(
                "Vienna, Austria",
                "Baroque streetscapes and imperial palaces set the stage for Vienna's artistic and musical masterpieces alongside its coffee-house culture and vibrant epicurean and design scenes.",
                "https://img.theculturetrip.com/x/smart/wp-content/uploads/2019/04/eur-austria-vienna.jpg",
                "https://flagpedia.net/data/flags/w580/at.png"));
        AllPlaces.add(new Place(
                "Bratislava, Slovakia",
                "Slovakia's capital since the country's independence in 1993, Bratislava is a mosaic of illustrious history: a medieval and Gothic old town, baroque palaces commissioned by Hungarian nobles, and the crowning castle, rebuilt to Renaissance finery.",
                "https://s27363.pcdn.co/wp-content/uploads/2018/08/Bratislava-Slovakia-1129x752.jpg.optimal.jpg",
                "https://flagpedia.net/data/flags/w580/sk.png"));
        AllPlaces.add(new Place(
                "Prague, Czech Republic",
                "Prague is the equal of Paris in terms of beauty. Its history goes back a millennium. And the beer? The best in Europe.",
                "https://cdn.thecrazytourist.com/wp-content/uploads/2018/06/ccimage-shutterstock_428804917.jpg",
                "https://flagpedia.net/data/flags/w580/cz.png"));
        AllPlaces.add(new Place(
                "Kiev, Ukraine",
                "Long before Ukraine and Russia existed, the city's inhabitants were already striding up and down the green hills, idling hot afternoons away on the Dnipro River and promenading along Khreshchatyk then a stream, now the main avenue.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/a6469f3993128cbcdde6e2b0a90aa1d3-kyiv.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/ua.png"));
        AllPlaces.add(new Place(
                "Warsaw, Poland",
                "A phoenix arisen from the ashes, Poland's capital impresses with its resilience, respect for history, contemporary style and sheer joie de vivre.",
                "https://www.roughguides.com/wp-content/uploads/2013/04/warsaw-old-town-royal-castle-poland-shutterstock_1171262353.jpg",
                "https://flagpedia.net/data/flags/w580/pl.png"));
        AllPlaces.add(new Place(
                "Minsk, Belarus",
                "Minsk will almost certainly surprise you. The capital of Belarus is, contrary to its dreary reputation, a progressive, modern and clean place.",
                "https://lonelyplanetimages.imgix.net/mastheads/513008517_large.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/by.png"));
        AllPlaces.add(new Place(
                "Vilnius, Lithuania",
                "There is a dreamy quality to Vilnius (vil-nyus), especially in the golden glow of a midsummer evening.",
                "https://content.r9cdn.net/rimg/dimg/09/c0/92c9d67d-city-7110-162f78f1fbc.jpg?crop=true&width=1000&height=600&xhint=2126&yhint=1263",
                "https://flagpedia.net/data/flags/w580/lt.png"));
        AllPlaces.add(new Place(
                "Riga, Latvia",
                "The Gothic spires that dominate Rīga's cityscape might suggest austerity, but it is the flamboyant art nouveau that forms the flesh and the spirit of this vibrant cosmopolitan city, the largest of all three Baltic capitals.",
                "https://www.roadaffair.com/wp-content/uploads/2019/03/house-blackheads-saint-peters-church-riga-latvia-shutterstock_1070326685-1024x683.jpg",
                "https://flagpedia.net/data/flags/w580/lv.png"));
        AllPlaces.add(new Place(
                "Tallinn, Estonia",
                "No longer the plaything of greater powers – Danish, Swedish, Polish, German and Soviet – Tallinn is now a proud European capital with an allure all of its own.",
                "https://www.nomadepicureans.com/wp-content/uploads/2019/05/Tallinn-Walking-Tour-Feature-Image.jpg",
                "https://flagpedia.net/data/flags/w580/ee.png"));
        AllPlaces.add(new Place(
                "Helsinki, Finland",
                "Entwined with the Baltic's bays, inlets and islands, Helsinki's boulevards and backstreets overflow with magnificent architecture, intriguing drinking and dining venues and groundbreaking design.",
                "http://www.hel.fi/wps/wcm/connect/5a20f5c2-6516-40e7-a239-ef6585fdfcfd/englkielisetpalvelutuutiskuva.jpg?MOD=AJPERES&CACHEID=ROOTWORKSPACE-5a20f5c2-6516-40e7-a239-ef6585fdfcfd-mz18k6W",
                "https://flagpedia.net/data/flags/w580/fi.png"));
        AllPlaces.add(new Place(
                "Stockholm, Sweden",
                "Stockholmers call their city 'beauty on water'. But despite the well-preserved historic core, Stockholm is no museum piece: it's modern, dynamic and ever-evolving.",
                "https://a.cdn-hotels.com/gdcs/production151/d1898/abc9d800-c31d-11e8-825c-0242ac110006.jpg",
                "https://flagpedia.net/data/flags/w580/se.png"));
        AllPlaces.add(new Place(
                "Reykjavik, Iceland",
                "The world’s most northerly capital combines colourful buildings, quirky, creative people, eye-popping design, wild nightlife and a capricious soul.",
                "https://www.roughguides.com/wp-content/uploads/2013/01/hallgrimskirkja-cathedral-city-reykjavik-iceland-shutterstock_613997816.jpg",
                "https://flagpedia.net/data/flags/w580/is.png"));
        AllPlaces.add(new Place(
                "Oslo, Norway",
                "Surrounded by mountains and sea, this compact, cultured and fun city has a palpable sense of reinvention.",
                "https://cdn-image.travelandleisure.com/sites/default/files/styles/1600x1000/public/1498156851/opera-house-oslo-norway-OSLO0617.jpg?itok=rcuoL2da",
                "https://flagpedia.net/data/flags/w580/no.png"));
        AllPlaces.add(new Place(
                "Copenhagen, Denmark",
                "Copenhagen is the epitome of Scandi cool. Modernist lamps light New Nordic tables, bridges buzz with cycling commuters and eye-candy locals dive into pristine waterways.",
                "https://lonelyplanetimages.imgix.net/mastheads/GettyImages-126140612_super%20.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/dk.png"));
        AllPlaces.add(new Place(
                "Amsterdam, Netherlands",
                "Golden Age canals lined by tilting gabled buildings are the backdrop for Amsterdam's treasure-packed museums, vintage-filled shops and hyper-creative drinking, dining and design scenes.",
                "http://cdn.cnn.com/cnnnext/dam/assets/190315131613-07-netherlands-happiest-coutries-2018.jpg",
                "https://flagpedia.net/data/flags/w580/nl.png"));
        AllPlaces.add(new Place(
                "Dublin, Ireland",
                "A small capital with a huge reputation, Dublin has a mix of heritage and hedonism that will not disappoint. All you have to do is show up.",
                "https://thesefootballtimes.co/wp-content/uploads/2015/10/Dublin-Ireland.jpg",
                "https://flagpedia.net/data/flags/w580/ie.png"));
        AllPlaces.add(new Place(
                "Brussels, Belgium",
                "Historic yet hip, bureaucratic yet bizarre, self-confident yet unshowy, Brussels is multicultural to its roots.",
                "https://media.timeout.com/images/105237721/image.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/Flag_of_Belgium.svg/2000px-Flag_of_Belgium.svg.png"));
        AllPlaces.add(new Place(
                "Berlin, Germany",
                "Berlin's combo of glamour and grit is bound to mesmerise all those keen to explore its vibrant culture, cutting-edge architecture, fabulous food, intense parties and tangible history.",
                "https://www.berlin-welcomecard.de/sites/default/files/styles/stage_desktop_20x/public/berlin-welcomecard_hero_2880__1.jpg?itok=fgbQwjEO&timestamp=1528123533",
                "https://flagpedia.net/data/flags/ultra/de.png"));
        AllPlaces.add(new Place(
                "Paris, France",
                "Paris' monument-lined boulevards, museums, classical bistros and boutiques are enhanced by a new wave of multimedia galleries, creative wine bars, design shops and tech start-ups.",
                "https://www.fodors.com/wp-content/uploads/2018/10/HERO_UltimateParis_Heroshutterstock_112137761.jpg",
                "https://flagpedia.net/data/flags/ultra/fr.png"));
        AllPlaces.add(new Place(
                "Lisbon, Portugal",
                "Seven cinematic hillsides overlooking the Rio Tejo cradle Lisbon's postcard-perfect panorama of cobbled alleyways, ancient ruins and white-domed cathedrals a captivating scene crafted over centuries.",
                "https://www.lisbonguru.com/wp-content/uploads/2015/11/viewpoints-lisbon.jpg",
                "https://flagpedia.net/data/flags/ultra/pt.png"));
        AllPlaces.add(new Place(
                "Madrid, Spain",
                "Madrid is a beguiling place with an energy that carries one simple message: this city really knows how to live.",
                "https://avisassets.abgemea.com/dam/jcr:afda6cf9-fc35-4c47-940a-baa594ab04ed/Grand_Via_Madrid_Credit_iStock_kasto80.jpg",
                "https://flagpedia.net/data/flags/ultra/es.png"));
        AllPlaces.add(new Place(
                "London, United Kingdom",
                "Immersed in history, London's rich seams of eye-opening antiquity are everywhere.",
                "http://qarva.com/wp-content/gallery/ott-2018/london-background-14.jpeg",
                "https://flagpedia.net/data/flags/ultra/gb.png"));
        AllPlaces.add(new Place(
                "Santorini, Greece",
                "Santorini is the supermodel of the Greek islands, a head-turner whose face is instantly recognisable around the world: multicoloured cliffs soar out of a sea-drowned caldera, topped by drifts of whitewashed buildings.",
                "https://dak95nwic4sny.cloudfront.net/73/santorini-44228168-1550653710-ImageGalleryLightboxLarge.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Flag_of_Greece.svg/2000px-Flag_of_Greece.svg.png"));
        AllPlaces.add(new Place(
                "California, USA",
                "From misty Northern California redwood forests to sun-kissed Southern California beaches, the enchanted Golden State makes Disneyland seem normal.",
                "https://cdn.vox-cdn.com/thumbor/-8P2BKe5-vuV5wrlhVPjZNPERrU=/0x0:4288x2848/1200x800/filters:focal(1985x1801:2671x2487)/cdn.vox-cdn.com/uploads/chorus_image/image/58838825/shutterstock_604520357.0.jpg",
                "https://flagpedia.net/data/flags/w580/us.png"));
        adapter.notifyDataSetChanged();
    }

    public void addAsiaAustralia()
    {
        AllPlaces.clear();
        AllPlaces.add(new Place(
                "Palawan, Philippines",
                "Nothing defines Palawan more than the water around it.",
                "https://www.leisurepro.com/blog/wp-content/uploads/2019/01/shutterstock_587590205-1366x800@2x.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/99/Flag_of_the_Philippines.svg/2000px-Flag_of_the_Philippines.svg.png"));
        AllPlaces.add(new Place(
                "Sydney, Australia",
                "Sydney, spectacularly draped around its glorious harbour and beaches, has visual wow factor like few other cities. Scratch the surface and it only gets better.",
                "https://www.tourstogo.com.au/things-to-do/wp-content/uploads/2018/12/Sydney-Featured.jpg",
                "https://static.grainger.com/rp/s/is/image/Grainger/5JFT9_AL01"));
        adapter.notifyDataSetChanged();
    }

    public void addAsiaAfrica()
    {
        AllPlaces.clear();
        AllPlaces.add(new Place(
                "Palawan, Philippines",
                "Nothing defines Palawan more than the water around it.",
                "https://www.leisurepro.com/blog/wp-content/uploads/2019/01/shutterstock_587590205-1366x800@2x.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/99/Flag_of_the_Philippines.svg/2000px-Flag_of_the_Philippines.svg.png"));
        AllPlaces.add(new Place(
                "Nairobi, Kenya",
                "East Africa's most cosmopolitan city, Nairobi is Kenya's beating heart, an exciting, maddening concrete jungle that jarringly counterpoints the untrammelled natural beauty to be found elsewhere in the country.",
                "https://dak95nwic4sny.cloudfront.net/73/kenya-beach-and-safari-43962495-1554105066-ImageGalleryLightboxLarge.jpg",
                "https://flagpedia.net/data/flags/w580/ke.png"));
        adapter.notifyDataSetChanged();
    }

    public void addAsiaSouthAmerica()
    {
        AllPlaces.clear();
        AllPlaces.add(new Place(
                "Palawan, Philippines",
                "Nothing defines Palawan more than the water around it.",
                "https://www.leisurepro.com/blog/wp-content/uploads/2019/01/shutterstock_587590205-1366x800@2x.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/99/Flag_of_the_Philippines.svg/2000px-Flag_of_the_Philippines.svg.png"));
        AllPlaces.add(new Place(
                "Buenos Aires, Argentina",
                "Beautiful, defiant and intense, Argentina seduces with its streetside tango, wafting grills, fútbol (soccer), gaucho culture and the mighty Andes. It's one formidable cocktail of wanderlust.",
                "https://d1bvpoagx8hqbg.cloudfront.net/originals/experience-buenos-aires-argentina-evelyn-4f019adee7f47b76494b404b3553e354.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1a/Flag_of_Argentina.svg/2000px-Flag_of_Argentina.svg.png"));
        adapter.notifyDataSetChanged();
    }

    public void addAsiaNorthAmerica()
    {
        AllPlaces.clear();
        AllPlaces.add(new Place(
                "Palawan, Philippines",
                "Nothing defines Palawan more than the water around it.",
                "https://www.leisurepro.com/blog/wp-content/uploads/2019/01/shutterstock_587590205-1366x800@2x.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/99/Flag_of_the_Philippines.svg/2000px-Flag_of_the_Philippines.svg.png"));
        AllPlaces.add(new Place(
                "California, USA",
                "From misty Northern California redwood forests to sun-kissed Southern California beaches, the enchanted Golden State makes Disneyland seem normal.",
                "https://cdn.vox-cdn.com/thumbor/-8P2BKe5-vuV5wrlhVPjZNPERrU=/0x0:4288x2848/1200x800/filters:focal(1985x1801:2671x2487)/cdn.vox-cdn.com/uploads/chorus_image/image/58838825/shutterstock_604520357.0.jpg",
                "https://flagpedia.net/data/flags/w580/us.png"));
        adapter.notifyDataSetChanged();
    }

    public void addAustraliaAfrica()
    {
        AllPlaces.clear();
        AllPlaces.add(new Place(
                "Sydney, Australia",
                "Sydney, spectacularly draped around its glorious harbour and beaches, has visual wow factor like few other cities. Scratch the surface and it only gets better.",
                "https://www.tourstogo.com.au/things-to-do/wp-content/uploads/2018/12/Sydney-Featured.jpg",
                "https://static.grainger.com/rp/s/is/image/Grainger/5JFT9_AL01"));
        AllPlaces.add(new Place(
                "Nairobi, Kenya",
                "East Africa's most cosmopolitan city, Nairobi is Kenya's beating heart, an exciting, maddening concrete jungle that jarringly counterpoints the untrammelled natural beauty to be found elsewhere in the country.",
                "https://dak95nwic4sny.cloudfront.net/73/kenya-beach-and-safari-43962495-1554105066-ImageGalleryLightboxLarge.jpg",
                "https://flagpedia.net/data/flags/w580/ke.png"));
        adapter.notifyDataSetChanged();
    }

    public void addAustraliaSouthAmerica()
    {
        AllPlaces.clear();
        AllPlaces.add(new Place(
                "Sydney, Australia",
                "Sydney, spectacularly draped around its glorious harbour and beaches, has visual wow factor like few other cities. Scratch the surface and it only gets better.",
                "https://www.tourstogo.com.au/things-to-do/wp-content/uploads/2018/12/Sydney-Featured.jpg",
                "https://static.grainger.com/rp/s/is/image/Grainger/5JFT9_AL01"));
        AllPlaces.add(new Place(
                "Buenos Aires, Argentina",
                "Beautiful, defiant and intense, Argentina seduces with its streetside tango, wafting grills, fútbol (soccer), gaucho culture and the mighty Andes. It's one formidable cocktail of wanderlust.",
                "https://d1bvpoagx8hqbg.cloudfront.net/originals/experience-buenos-aires-argentina-evelyn-4f019adee7f47b76494b404b3553e354.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1a/Flag_of_Argentina.svg/2000px-Flag_of_Argentina.svg.png"));
        adapter.notifyDataSetChanged();
    }

    public void addAustraliaNorthAmerica()
    {
        AllPlaces.clear();
        AllPlaces.add(new Place(
                "Sydney, Australia",
                "Sydney, spectacularly draped around its glorious harbour and beaches, has visual wow factor like few other cities. Scratch the surface and it only gets better.",
                "https://www.tourstogo.com.au/things-to-do/wp-content/uploads/2018/12/Sydney-Featured.jpg",
                "https://static.grainger.com/rp/s/is/image/Grainger/5JFT9_AL01"));
        AllPlaces.add(new Place(
                "California, USA",
                "From misty Northern California redwood forests to sun-kissed Southern California beaches, the enchanted Golden State makes Disneyland seem normal.",
                "https://cdn.vox-cdn.com/thumbor/-8P2BKe5-vuV5wrlhVPjZNPERrU=/0x0:4288x2848/1200x800/filters:focal(1985x1801:2671x2487)/cdn.vox-cdn.com/uploads/chorus_image/image/58838825/shutterstock_604520357.0.jpg",
                "https://flagpedia.net/data/flags/w580/us.png"));
        adapter.notifyDataSetChanged();
    }

    public void addAfricaSouthAmerica()
    {
        AllPlaces.clear();
        AllPlaces.add(new Place(
                "Nairobi, Kenya",
                "East Africa's most cosmopolitan city, Nairobi is Kenya's beating heart, an exciting, maddening concrete jungle that jarringly counterpoints the untrammelled natural beauty to be found elsewhere in the country.",
                "https://dak95nwic4sny.cloudfront.net/73/kenya-beach-and-safari-43962495-1554105066-ImageGalleryLightboxLarge.jpg",
                "https://flagpedia.net/data/flags/w580/ke.png"));
        AllPlaces.add(new Place(
                "Buenos Aires, Argentina",
                "Beautiful, defiant and intense, Argentina seduces with its streetside tango, wafting grills, fútbol (soccer), gaucho culture and the mighty Andes. It's one formidable cocktail of wanderlust.",
                "https://d1bvpoagx8hqbg.cloudfront.net/originals/experience-buenos-aires-argentina-evelyn-4f019adee7f47b76494b404b3553e354.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1a/Flag_of_Argentina.svg/2000px-Flag_of_Argentina.svg.png"));
        adapter.notifyDataSetChanged();
    }

    public void addAfricaNorthAmerica()
    {
        AllPlaces.clear();
        AllPlaces.add(new Place(
                "Nairobi, Kenya",
                "East Africa's most cosmopolitan city, Nairobi is Kenya's beating heart, an exciting, maddening concrete jungle that jarringly counterpoints the untrammelled natural beauty to be found elsewhere in the country.",
                "https://dak95nwic4sny.cloudfront.net/73/kenya-beach-and-safari-43962495-1554105066-ImageGalleryLightboxLarge.jpg",
                "https://flagpedia.net/data/flags/w580/ke.png"));
        AllPlaces.add(new Place(
                "California, USA",
                "From misty Northern California redwood forests to sun-kissed Southern California beaches, the enchanted Golden State makes Disneyland seem normal.",
                "https://cdn.vox-cdn.com/thumbor/-8P2BKe5-vuV5wrlhVPjZNPERrU=/0x0:4288x2848/1200x800/filters:focal(1985x1801:2671x2487)/cdn.vox-cdn.com/uploads/chorus_image/image/58838825/shutterstock_604520357.0.jpg",
                "https://flagpedia.net/data/flags/w580/us.png"));
        adapter.notifyDataSetChanged();
    }

    public void addSouthNorthAmerica()
    {
        AllPlaces.clear();
        AllPlaces.add(new Place(
                "Buenos Aires, Argentina",
                "Beautiful, defiant and intense, Argentina seduces with its streetside tango, wafting grills, fútbol (soccer), gaucho culture and the mighty Andes. It's one formidable cocktail of wanderlust.",
                "https://d1bvpoagx8hqbg.cloudfront.net/originals/experience-buenos-aires-argentina-evelyn-4f019adee7f47b76494b404b3553e354.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1a/Flag_of_Argentina.svg/2000px-Flag_of_Argentina.svg.png"));
        AllPlaces.add(new Place(
                "California, USA",
                "From misty Northern California redwood forests to sun-kissed Southern California beaches, the enchanted Golden State makes Disneyland seem normal.",
                "https://cdn.vox-cdn.com/thumbor/-8P2BKe5-vuV5wrlhVPjZNPERrU=/0x0:4288x2848/1200x800/filters:focal(1985x1801:2671x2487)/cdn.vox-cdn.com/uploads/chorus_image/image/58838825/shutterstock_604520357.0.jpg",
                "https://flagpedia.net/data/flags/w580/us.png"));
        adapter.notifyDataSetChanged();
    }

//    SEASON WITH CONTINENT COMBINATION IS UNDER CONSTRUCTION


    public void allPlacesList()
    {
        AllPlaces = new ArrayList<>();

        AllPlaces.add(new Place(
                "Skopje, Macedonia",
                "Skopje has plenty of charm. Its Ottoman- and Byzantine-era sights are focused around the city's delightful Čaršija, bordered by the 15th-century Kameni Most (Stone Bridge) and Tvrdina Kale Fortress – Skopje's guardian since the 5th century.",
                "https://lonelyplanetimages.imgix.net/mastheads/83405857%20.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/mk.png"));
        AllPlaces.add(new Place(
                "Pristina, Kosovo",
                "Pristina is a fast-changing city that feels full of optimism and potential, even if its traffic-clogged streets and mismatched architectural styles don't make it an obviously attractive place.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/e228bf3be784ffff7a338ec8d9167d30-pristina.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/ks.png"));
        AllPlaces.add(new Place(
                "Tirana, Albania",
                "Lively, colourful Tirana is where this tiny nation's hopes and dreams coalesce into a vibrant whirl of traffic, brash consumerism and unfettered fun.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/25e80d94ce0c6dfee0634a2b8598feae-tirana.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/al.png"));
        AllPlaces.add(new Place(
                "Podgorica, Montenegro",
                "Given it’s undergone five name changes, passed through the hands of everyone from the Romans to the Turks to the Austro-Hungarians, and twice been wiped off the map entirely, it’s little wonder that Podgorica (Подгорица) seems permanently gripped by an identity crisis.",
                "https://lonelyplanetimages.imgix.net/mastheads/147031066_medium.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/me.png"));
        AllPlaces.add(new Place(
                "Sarajevo, Bosnia Herzegovina",
                "Ringed by mountains, Sarajevo is a singular city with an enticing East-meets-West vibe all of its own. It was once renowned as a religious melting pot, earning it the epithet 'the Jerusalem of Europe'.",
                "https://s23835.pcdn.co/wp-content/uploads/2018/07/Bosnia-Sarajevo-view-4.jpg",
                "https://flagpedia.net/data/flags/w580/ba.png"));
        AllPlaces.add(new Place(
                "Belgrade, Serbia",
                "Outspoken, adventurous, proud and audacious: Belgrade ('White City') is by no means a 'pretty' capital, but its gritty exuberance makes it one of Europe's most happening cities.",
                "https://media.architecturaldigest.com/photos/5d37554e47a77b000872bbf4/16:9/w_1280,c_limit/GettyImages-847708760.jpg",
                "https://flagpedia.net/data/flags/w580/rs.png"));
        AllPlaces.add(new Place(
                "Zagreb, Croatia",
                "Zagreb is made for strolling. Wander through the Upper Town's red-roof and cobblestone glory, peppered with church spires.",
                "https://brightcove04pmdo-a.akamaihd.net/5104226627001/5104226627001_5441113691001_5428833703001-vs.jpg?pubId=5104226627001&videoId=5428833703001",
                "https://flagpedia.net/data/flags/w580/hr.png"));
        AllPlaces.add(new Place(
                "Ljubljana, Slovenia",
                "Slovenia's capital and largest city is one of Europe's greenest and most liveable capitals; it was the European Commission's Green Capital of Europe in 2016.",
                "https://lonelyplanetimages.imgix.net/mastheads/GettyImages-494314513_high.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/si.png"));
        AllPlaces.add(new Place(
                "Sofia, Bulgaria",
                "Bulgaria's pleasingly laid-back capital is often overlooked by visitors heading to the coast or the ski resorts, but they're missing something special.",
                "https://happytowander.com/wp-content/uploads/Sofia-Bulgaria-Happy-to-Wander-0609.jpg",
                "https://flagpedia.net/data/flags/w580/bg.png"));
        AllPlaces.add(new Place(
                "Istanbul, Turkey",
                "This magical meeting place of East and West has more top-drawer attractions than it has minarets (and that's a lot).",
                "http://blog.radissonblu.com/wp-content/uploads/2017/07/Blue-Mosque.jpg",
                "https://flagpedia.net/data/flags/w580/tr.png"));
        AllPlaces.add(new Place(
                "Chisinau, Moldova",
                "Pretty much all roads in Moldova lead to its wine-and-food loving capital.",
                "https://d1z15fh6odiy9s.cloudfront.net/plonk/adfb.../adfba780ee49dd02aa788772e39b7b44497517c80233d3e1cbad1c138f694a02-large.jpg",
                "https://flagpedia.net/data/flags/w580/md.png"));
        AllPlaces.add(new Place(
                "Bucharest, Romania",
                "Romania’s capital sometimes gets a bad rap, but in fact it's dynamic, energetic and lots of fun. Many travellers give the city just a night or two before heading off to Transylvania, but that’s not enough time.",
                "https://www.romania-insider.com/sites/default/files/styles/article_large_image/public/2019-02/bucharest_universitatii_square_-_shutterstock.jpg",
                "https://flagpedia.net/data/flags/w580/ro.png"));
        AllPlaces.add(new Place(
                "Budapest, Hungary",
                "Budapest has something for everyone – from dramatic history and flamboyant architecture to healing thermal waters and a nightlife that is unrivalled in Eastern and Central Europe.",
                "https://brightcove04pmdo-a.akamaihd.net/5104226627001/5104226627001_5515999951001_5214933106001-vs.jpg?pubId=5104226627001&videoId=5214933106001",
                "https://flagpedia.net/data/flags/w580/hu.png"));
        AllPlaces.add(new Place(
                "Rome, Italy",
                "A heady mix of haunting ruins, awe-inspiring art and vibrant street life, Italy’s hot-blooded capital is one of the world’s most romantic and charismatic cities.",
                "https://cdn.citywonders.com/media/17764/rome-city.jpg?anchor=center&mode=crop&width=1200&height=600",
                "https://flagpedia.net/data/flags/w580/it.png"));
        AllPlaces.add(new Place(
                "Bern, Switzerland",
                "Wandering through the picture-postcard, Unesco World Heritage–listed Old Town, with its provincial, laid-back air, it's hard to believe that Bern (Berne in French) is the capital of Switzerland.",
                "https://d2mpqlmtgl1znu.cloudfront.net/AcuCustom/Sitename/DAM/017/Bern_Adobe.jpg",
                "https://flagpedia.net/data/flags/w580/ch.png"));
        AllPlaces.add(new Place(
                "Vienna, Austria",
                "Baroque streetscapes and imperial palaces set the stage for Vienna's artistic and musical masterpieces alongside its coffee-house culture and vibrant epicurean and design scenes.",
                "https://img.theculturetrip.com/x/smart/wp-content/uploads/2019/04/eur-austria-vienna.jpg",
                "https://flagpedia.net/data/flags/w580/at.png"));
        AllPlaces.add(new Place(
                "Bratislava, Slovakia",
                "Slovakia's capital since the country's independence in 1993, Bratislava is a mosaic of illustrious history: a medieval and Gothic old town, baroque palaces commissioned by Hungarian nobles, and the crowning castle, rebuilt to Renaissance finery.",
                "https://s27363.pcdn.co/wp-content/uploads/2018/08/Bratislava-Slovakia-1129x752.jpg.optimal.jpg",
                "https://flagpedia.net/data/flags/w580/sk.png"));
        AllPlaces.add(new Place(
                "Prague, Czech Republic",
                "Prague is the equal of Paris in terms of beauty. Its history goes back a millennium. And the beer? The best in Europe.",
                "https://cdn.thecrazytourist.com/wp-content/uploads/2018/06/ccimage-shutterstock_428804917.jpg",
                "https://flagpedia.net/data/flags/w580/cz.png"));
        AllPlaces.add(new Place(
                "Kiev, Ukraine",
                "Long before Ukraine and Russia existed, the city's inhabitants were already striding up and down the green hills, idling hot afternoons away on the Dnipro River and promenading along Khreshchatyk then a stream, now the main avenue.",
                "https://lonelyplanetimages.imgix.net/a/g/hi/t/a6469f3993128cbcdde6e2b0a90aa1d3-kyiv.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/ua.png"));
        AllPlaces.add(new Place(
                "Warsaw, Poland",
                "A phoenix arisen from the ashes, Poland's capital impresses with its resilience, respect for history, contemporary style and sheer joie de vivre.",
                "https://www.roughguides.com/wp-content/uploads/2013/04/warsaw-old-town-royal-castle-poland-shutterstock_1171262353.jpg",
                "https://flagpedia.net/data/flags/w580/pl.png"));
        AllPlaces.add(new Place(
                "Minsk, Belarus",
                "Minsk will almost certainly surprise you. The capital of Belarus is, contrary to its dreary reputation, a progressive, modern and clean place.",
                "https://lonelyplanetimages.imgix.net/mastheads/513008517_large.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/by.png"));
        AllPlaces.add(new Place(
                "Vilnius, Lithuania",
                "There is a dreamy quality to Vilnius (vil-nyus), especially in the golden glow of a midsummer evening.",
                "https://content.r9cdn.net/rimg/dimg/09/c0/92c9d67d-city-7110-162f78f1fbc.jpg?crop=true&width=1000&height=600&xhint=2126&yhint=1263",
                "https://flagpedia.net/data/flags/w580/lt.png"));
        AllPlaces.add(new Place(
                "Riga, Latvia",
                "The Gothic spires that dominate Rīga's cityscape might suggest austerity, but it is the flamboyant art nouveau that forms the flesh and the spirit of this vibrant cosmopolitan city, the largest of all three Baltic capitals.",
                "https://www.roadaffair.com/wp-content/uploads/2019/03/house-blackheads-saint-peters-church-riga-latvia-shutterstock_1070326685-1024x683.jpg",
                "https://flagpedia.net/data/flags/w580/lv.png"));
        AllPlaces.add(new Place(
                "Tallinn, Estonia",
                "No longer the plaything of greater powers – Danish, Swedish, Polish, German and Soviet – Tallinn is now a proud European capital with an allure all of its own.",
                "https://www.nomadepicureans.com/wp-content/uploads/2019/05/Tallinn-Walking-Tour-Feature-Image.jpg",
                "https://flagpedia.net/data/flags/w580/ee.png"));
        AllPlaces.add(new Place(
                "Helsinki, Finland",
                "Entwined with the Baltic's bays, inlets and islands, Helsinki's boulevards and backstreets overflow with magnificent architecture, intriguing drinking and dining venues and groundbreaking design.",
                "http://www.hel.fi/wps/wcm/connect/5a20f5c2-6516-40e7-a239-ef6585fdfcfd/englkielisetpalvelutuutiskuva.jpg?MOD=AJPERES&CACHEID=ROOTWORKSPACE-5a20f5c2-6516-40e7-a239-ef6585fdfcfd-mz18k6W",
                "https://flagpedia.net/data/flags/w580/fi.png"));
        AllPlaces.add(new Place(
                "Stockholm, Sweden",
                "Stockholmers call their city 'beauty on water'. But despite the well-preserved historic core, Stockholm is no museum piece: it's modern, dynamic and ever-evolving.",
                "https://a.cdn-hotels.com/gdcs/production151/d1898/abc9d800-c31d-11e8-825c-0242ac110006.jpg",
                "https://flagpedia.net/data/flags/w580/se.png"));
        AllPlaces.add(new Place(
                "Reykjavik, Iceland",
                "The world’s most northerly capital combines colourful buildings, quirky, creative people, eye-popping design, wild nightlife and a capricious soul.",
                "https://www.roughguides.com/wp-content/uploads/2013/01/hallgrimskirkja-cathedral-city-reykjavik-iceland-shutterstock_613997816.jpg",
                "https://flagpedia.net/data/flags/w580/is.png"));
        AllPlaces.add(new Place(
                "Oslo, Norway",
                "Surrounded by mountains and sea, this compact, cultured and fun city has a palpable sense of reinvention.",
                "https://cdn-image.travelandleisure.com/sites/default/files/styles/1600x1000/public/1498156851/opera-house-oslo-norway-OSLO0617.jpg?itok=rcuoL2da",
                "https://flagpedia.net/data/flags/w580/no.png"));
        AllPlaces.add(new Place(
                "Copenhagen, Denmark",
                "Copenhagen is the epitome of Scandi cool. Modernist lamps light New Nordic tables, bridges buzz with cycling commuters and eye-candy locals dive into pristine waterways.",
                "https://lonelyplanetimages.imgix.net/mastheads/GettyImages-126140612_super%20.jpg?sharp=10&vib=20&w=1200",
                "https://flagpedia.net/data/flags/w580/dk.png"));
        AllPlaces.add(new Place(
                "Amsterdam, Netherlands",
                "Golden Age canals lined by tilting gabled buildings are the backdrop for Amsterdam's treasure-packed museums, vintage-filled shops and hyper-creative drinking, dining and design scenes.",
                "http://cdn.cnn.com/cnnnext/dam/assets/190315131613-07-netherlands-happiest-coutries-2018.jpg",
                "https://flagpedia.net/data/flags/w580/nl.png"));
        AllPlaces.add(new Place(
                "Dublin, Ireland",
                "A small capital with a huge reputation, Dublin has a mix of heritage and hedonism that will not disappoint. All you have to do is show up.",
                "https://thesefootballtimes.co/wp-content/uploads/2015/10/Dublin-Ireland.jpg",
                "https://flagpedia.net/data/flags/w580/ie.png"));
        AllPlaces.add(new Place(
                "Brussels, Belgium",
                "Historic yet hip, bureaucratic yet bizarre, self-confident yet unshowy, Brussels is multicultural to its roots.",
                "https://media.timeout.com/images/105237721/image.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/Flag_of_Belgium.svg/2000px-Flag_of_Belgium.svg.png"));
        AllPlaces.add(new Place(
                "Berlin, Germany",
                "Berlin's combo of glamour and grit is bound to mesmerise all those keen to explore its vibrant culture, cutting-edge architecture, fabulous food, intense parties and tangible history.",
                "https://www.berlin-welcomecard.de/sites/default/files/styles/stage_desktop_20x/public/berlin-welcomecard_hero_2880__1.jpg?itok=fgbQwjEO&timestamp=1528123533",
                "https://flagpedia.net/data/flags/ultra/de.png"));
        AllPlaces.add(new Place(
                "Paris, France",
                "Paris' monument-lined boulevards, museums, classical bistros and boutiques are enhanced by a new wave of multimedia galleries, creative wine bars, design shops and tech start-ups.",
                "https://www.fodors.com/wp-content/uploads/2018/10/HERO_UltimateParis_Heroshutterstock_112137761.jpg",
                "https://flagpedia.net/data/flags/ultra/fr.png"));
        AllPlaces.add(new Place(
                "Lisbon, Portugal",
                "Seven cinematic hillsides overlooking the Rio Tejo cradle Lisbon's postcard-perfect panorama of cobbled alleyways, ancient ruins and white-domed cathedrals a captivating scene crafted over centuries.",
                "https://www.lisbonguru.com/wp-content/uploads/2015/11/viewpoints-lisbon.jpg",
                "https://flagpedia.net/data/flags/ultra/pt.png"));
        AllPlaces.add(new Place(
                "Madrid, Spain",
                "Madrid is a beguiling place with an energy that carries one simple message: this city really knows how to live.",
                "https://avisassets.abgemea.com/dam/jcr:afda6cf9-fc35-4c47-940a-baa594ab04ed/Grand_Via_Madrid_Credit_iStock_kasto80.jpg",
                "https://flagpedia.net/data/flags/ultra/es.png"));
        AllPlaces.add(new Place(
                "London, United Kingdom",
                "Immersed in history, London's rich seams of eye-opening antiquity are everywhere.",
                "http://qarva.com/wp-content/gallery/ott-2018/london-background-14.jpeg",
                "https://flagpedia.net/data/flags/ultra/gb.png"));
        AllPlaces.add(new Place(
                "Santorini, Greece",
                "Santorini is the supermodel of the Greek islands, a head-turner whose face is instantly recognisable around the world: multicoloured cliffs soar out of a sea-drowned caldera, topped by drifts of whitewashed buildings.",
                "https://dak95nwic4sny.cloudfront.net/73/santorini-44228168-1550653710-ImageGalleryLightboxLarge.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Flag_of_Greece.svg/2000px-Flag_of_Greece.svg.png"));

//        Asia

        AllPlaces.add(new Place(
                "Palawan, Philippines",
                "Nothing defines Palawan more than the water around it.",
                "https://www.leisurepro.com/blog/wp-content/uploads/2019/01/shutterstock_587590205-1366x800@2x.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/99/Flag_of_the_Philippines.svg/2000px-Flag_of_the_Philippines.svg.png"));

//       //Australia

        AllPlaces.add(new Place(
                "Sydney, Australia",
                "Sydney, spectacularly draped around its glorious harbour and beaches, has visual wow factor like few other cities. Scratch the surface and it only gets better.",
                "https://www.tourstogo.com.au/things-to-do/wp-content/uploads/2018/12/Sydney-Featured.jpg",
                "https://static.grainger.com/rp/s/is/image/Grainger/5JFT9_AL01"));

//        //South America

        AllPlaces.add(new Place(
                "Buenos Aires, Argentina",
                "Beautiful, defiant and intense, Argentina seduces with its streetside tango, wafting grills, fútbol (soccer), gaucho culture and the mighty Andes. It's one formidable cocktail of wanderlust.",
                "https://d1bvpoagx8hqbg.cloudfront.net/originals/experience-buenos-aires-argentina-evelyn-4f019adee7f47b76494b404b3553e354.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1a/Flag_of_Argentina.svg/2000px-Flag_of_Argentina.svg.png"));

//        //North America


        AllPlaces.add(new Place(
                "California, USA",
                "From misty Northern California redwood forests to sun-kissed Southern California beaches, the enchanted Golden State makes Disneyland seem normal.",
                "https://cdn.vox-cdn.com/thumbor/-8P2BKe5-vuV5wrlhVPjZNPERrU=/0x0:4288x2848/1200x800/filters:focal(1985x1801:2671x2487)/cdn.vox-cdn.com/uploads/chorus_image/image/58838825/shutterstock_604520357.0.jpg",
                "https://flagpedia.net/data/flags/w580/us.png"));

//        //Africa

        AllPlaces.add(new Place(
                "Nairobi, Kenya",
                "East Africa's most cosmopolitan city, Nairobi is Kenya's beating heart, an exciting, maddening concrete jungle that jarringly counterpoints the untrammelled natural beauty to be found elsewhere in the country.",
                "https://dak95nwic4sny.cloudfront.net/73/kenya-beach-and-safari-43962495-1554105066-ImageGalleryLightboxLarge.jpg",
                "https://flagpedia.net/data/flags/w580/ke.png"));

    }

    public void buildRecyclerView()
    {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        adapter = new PlaceAdapter(AllPlaces, getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);


        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (placeAdapter == null){
                    placeAdapter = new PlaceAdapter(AllPlaces, getApplicationContext());
                }
                placeAdapter.getFilter().filter(newText);

                return false;
            }
        });
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.Help:
                manager.beginTransaction()
                        .show(helpFrag)
                        .commit();
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(PlacesActivity.this, MainActivity.class);
        startActivity(intent);
        finish();


    }


    //I was trying to implement the SWITCH case method over the IF method above but I had no success
    //yet. I will need help with this in the future.

//    @Override
//    public void onClick(View view) {
//        boolean checked = ((CheckBox) view).isChecked();
//
//        switch (view.getId()) {
//            case R.id.check_spring:
//                if (checked) {
//                    removeAllplaces();
//                    addSpring();
//                    removeSummer();
//                    removeAutumn();
//                    removeWinter();
//                } else
//                    addAllplaces();
//                removeSpring();
//                removeSummer();
//                removeAutumn();
//                removeWinter();
//                break;
//            case R.id.check_summer:
//                if (checked) {
//                    removeAllplaces();
//                    removeSpring();
//                    addSummer();
//                    removeAutumn();
//                    removeWinter();
//                } else {
//                    addAllplaces();
//                    removeSpring();
//                    removeSummer();
//                    removeAutumn();
//                    removeWinter();
//                }
//
//                break;
//        }

}
