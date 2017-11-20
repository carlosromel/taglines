/**
 * Copyright (c) 2017 Carlos Romel Pereira da Silva, carlos.romel@gmail.com
 */
package br.jus.trtsp.sustentacaoregional.taglines;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author Carlos Romel Pereira da Silva, carlos.romel@gmail.com
 */
@Singleton
@Startup
public class RandomTagLine implements RandomTagLineRemote {

    private final List<String> taglines;

    public RandomTagLine() {

        this.taglines = Arrays.asList(
                "WHEN IT RAINS, IT POURS - Morton Salt, 1912",
                "SAY IT WITH FLOWERS - FTD Interflora, 1917",
                "AH! BISTO - Bisto Gravy Granules, 1919",
                "I’D WALK A MILE FOR A CAMEL - Camel Cigarettes, 1921",
                "ASK THE MAN WHO OWNS ONE - Packard, 1925",
                "THE PAUSE THAT REFRESHES - Coca Cola, 1927",
                "MMM MMM GOOD - Campbell’s Soup, 1930",
                "LIFTS AND SEPARATES - Playtex, 1930",
                "BREAKFAST OF CHAMPIONS - Wheaties, 1930",
                "SNAP! CRACKLE! POP! - Kellogg’s Rice Krispies, 1932",
                "WHEN YOU CARE ENOUGH TO SEND THE VERY BEST - Hallmark, 1934",
                "IF YOU WANT TO GET AHEAD, GET A HAT - Hat Council, 1934",
                "MY GOODNESS, MY GUINNESS - Guinness, 1936",
                "A DIAMOND IS FOREVER - DeBeers, 1948",
                "YOU’RE IN GOOD HANDS - Allstate, 1950",
                "A LITTLE DAB’LL DO YA - Brylcreem, 1950",
                "GRACE…SPACE…PACE - Jaguar, 1950",
                "THE CHAMPAGNE OF BOTTLE BEER - Miller Beer, 1950",
                "MELTS IN YOUR MOUTH, NOT IN YOUR HANDS - M&Ms, 1950",
                "IT TAKES A LICKING AND KEEPS ON TICKING - Times, 1950",
                "FINGER LICKIN’ GOOD - KFC, 1952",
                "PLOP, PLOP, FIZZ, FIZZ, OH WHAT A RELIEF IT IS - Alka Seltzer, 1953",
                "THE MILK CHOCOLATE MELTS IN YOUR MOUTH, NOT IN YOUR HAND - M&Ms, 1954",
                "GO TO WORK ON AN EGG - Egg Marketing Board, 1957",
                "HAVE A BREAK. HAVE A KIT KAT. - Rowntree, 1957",
                "LOOK MA, NO CAVITIES! - Crest Toothpaste, 1958",
                "GOOD TO THE LAST DROP - Maxwell House, 1959",
                "THINK SMALL - Volkswagen, 1959",
                "DOUBLE YOUR PLEASURE, DOUBLE YOUR FUN - Wrigley’s Doublemint, 1959",
                "WOTALOTIGOT! - Smarties, 1961",
                "WE TRY HARDER - Avis, 1962",
                "SCHH…YOU KNOW WHO - Schweppes, 1962",
                "PLEASE DON’T SQUEEZE THE CHARMIN - Charmin, 1964",
                "DOES SHE…OR DOESN’T SHE? - Clairol, 1964",
                "PUT A TIGER IN YOUR TANK - Esso, 1964",
                "LET YOUR FINGERS DO THE WALKING - Yellow Pages, 1964",
                "I CAN’T BELIEVE I ATE THE WHOLE THING - Alka Seltzer, 1966",
                "FLY THE FRIENDLY SKIES - United Airlines, 1966",
                "BEANZ MEANZ HEINZ - Heinz, 1967",
                "ALL BECAUSE THE LADY LOVES MILK TRAY - Cadbury UK, 1968",
                "CALGON, TAKE ME AWAY - Calgon, 1970",
                "IT’S THE REAL THING - Coca Cola, 1970",
                "CAN YOU EAT THREE SHREDDED WHEAT? - Nabisco, 1970",
                "I BET HE DRINKS CARLING BLACK LABEL - Carling, 1971",
                "IT TAKES A TOUGH MAN TO MAKE A TENDER CHICKEN - Perdue, 1972",
                "THE UNCOLA - 7UP, 1973",
                "HAVE IT YOUR WAY - Burger King, 1973",
                "PROBABLY THE BEST LAGER IN THE WORLD - Carlsberg, 1973",
                "TASTES GREAT, LESS FILLING - Miller Lite, 1974",
                "DON’T LEAVE HOME WITHOUT IT - American Express, 1975",
                "THE ULTIMATE DRIVING MACHINE - BMW, 1975",
                "I LOVE NEW YORK - NY State Dept., 1977",
                "YOUR FLEXIBLE FRIEND - Access Credit Card, 1978",
                "REACH OUT AND TOUCH SOMEONE - AT&T, 1979",
                "SHARE THE FANTASY - Chanel No. 5, 1979",
                "HAVE A COKE AND A SMILE - Coca Cola, 1979",
                "I LIKED IT SO MUCH, I BOUGHT THE COMPANY - Remington, 1979",
                "WE ALL ADORE A KIA-ORA - Kia-Ora, 1980",
                "A MARS A DAY HELPS YOU WORK, REST AND PLAY - Mars, 1980",
                "CENTRAL HEATING FOR KIDS - Ready Brek, 1980",
                "THE BANK THAT LIKES TO SAY YES - Trustee Savings Bank (TSB), 1980",
                "BETCHA CAN’T EAT JUST ONE - Lays, 1981",
                "BE ALL YOU CAN BE - The US Army, 1981",
                "THE APPLIANCE OF SCIENCE - Zanussi, 1981",
                "VORSPRUNG DURCH TECHNIK - Audi, 1982",
                "WHEN IT ABSOLUTELY, POSITIVELY HAS TO BE THERE OVERNIGHT - FedEx, 1982",
                "NO FT, NO COMMENT - Financial Times, 1982",
                "MILK’S GOTTA LOTTA BOTTLE - Milk Marketing Board, 1982",
                "AUSTRALIANS WOULDN’T GIVE A XXXX FOR ANYTHING ELSE - Castlemaine XXXX Beer, 1984",
                "DON’T JUST BOOK IT, THOMAS COOK IT - Thomas Cook, 1984",
                "HELLO TOSH, GOTTA TOSHIBA - Toshiba, 1984",
                "WHERE’S THE BEEF - Wendy’s, 1984",
                "THE OTHER WHITE MEAT - Pork, 1986",
                "THE CAR IN FRONT IS A TOYOTA - Toyota, 1986",
                "IT IS. ARE YOU? - The Independent, 1987",
                "I THINK, THEREFORE IBM - IBM, 1988",
                "JUST DO IT - Nike, 1988",
                "CATS LIKE FELIX LIKE FELIX - Felix Cat Food, 1989",
                "THE BEST A MAN CAN GET - Gillette Razors, 1989",
                "THINK DIFFERENT - Apple, 1990",
                "THE LISTENING BANK - Midland Bank, 1990",
                "ONCE YOU POP, YOU CAN’T STOP - Pringles, 1990",
                "MAYBE SHE’S BORN WITH IT - MAYBE IT’S MAYBELLINE L’Oreal, 1991",
                "IF IT’S ON, IT’S IN - Radio Times, 1991",
                "GOT MILK - California Milk Processor Board, 1993",
                "IT’S A BIT OF AN ANIMAL - Peperami, 1993",
                "I AM CANADIAN - Molson Beer, 1994",
                "THE FUTURE’S BRIGHT. THE FUTURE’S ORANGE - Orange, 1994",
                "DOES EXACTLY WHAT IT SAYS ON THE TIN - Ronseal, 1994",
                "FAIR AND BALANCED - Fox News Channel, 1995",
                "DRIVERS WANTED - Volkswagen, 1995",
                "HELLO BOYS - Wonderbra, 1995",
                "IT’S NOT TV, IT’S HBO - HBO, 1997",
                "THERE ARE SOME THINGS MONEY CAN’T BUY. FOR EVERYTHING ELSE, THERE’S MASTERCARD - Mastercard, 1997",
                "WHAT HAPPENS HERE, STAYS HERE - Las Vegas, 2002",
                "CAN YOU HEAR ME NOW - Verizon, 2002",
                "IMPOSSIBLE IS NOTHING - Adidas, 2004",
                "GET MORE - T-Mobile, 2005",
                "THE LAST PLACE YOU WANT TO GO - Dixons, 2009",
                "NO CHILD BORN TO DIE - Save The Children, 2011");
    }

    /**
     * Obtém uma tagline aleatória.
     *
     * @return uma tagline aleatória.
     */
    @Override
    public String getRandomTagLine() {

        return String.format("(%s)", this.taglines.get(new Random().nextInt(this.taglines.size())));
    }
}
