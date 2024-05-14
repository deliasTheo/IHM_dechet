package edu.polytech.ihmtd2dechet.objects;

public class GuideFactory {
    public static final int ENCOMBRANT = 1;
    public static final int NON_ENCOMBRANT = 2;
    public static final int TOXIQUE = 3;

    static public Guide build(int type) throws Throwable{
        switch (type){
            case ENCOMBRANT : {
                String infoGen = "Les déchets encombrants se réfèrent généralement" +
                        " à des articles de grande taille qui ne peuvent pas être " +
                        "jetés dans une poubelle standard. Cela inclut des objets " +
                        "tels que des meubles, des électroménagers, des matelas, " +
                        "des vélos, etc.";
                String comment = "Collecte Municipale : Vérifiez le calendrier de collecte des encombrants dans votre région." +
                        " Certains services municipaux proposent des collectes spéciales pour les gros articles." +
                        "Déchèterie ou Centre de Recyclage : Vous pouvez également apporter vos déchets encombrants à une" +
                        " déchèterie ou à un centre de recyclage désigné.";
                return new GuideEncombrant(infoGen, comment);
            }
            case NON_ENCOMBRANT :{
                String infoGen = "Ce sont des déchets qui peuvent être jetés avec les ordures ménagères" +
                        " régulières. Ce sont généralement des déchets de taille et de poids normaux," +
                        " tels que les emballages en carton, les plastiques, le verre, etc." ;
                String comment =
                        "1. Poubelle grise ou bac gris : pour les déchets non recyclables ou non compostables.\n" +
                                "2. Poubelle jaune ou bac jaune : pour les emballages recyclables tels que le plastique, le métal et le carton.\n" +
                                "3. Poubelle verte ou bac vert : pour les déchets organiques compostables comme les restes de fruits et légumes.\n" +
                                "4. Poubelle bleue ou bac bleu : pour les papiers et cartons à recycler.\n" +
                                "5. Poubelle marron ou bac marron : pour les déchets végétaux destinés au compostage.\n" ;

                return new GuideNonEncombrant(infoGen, comment);
            }
            case TOXIQUE :{
                String infoGen = "Ce sont des déchets qui contiennent des substances nocives pour" +
                        " l'environnement ou la santé humaine. Cela inclut les piles, les produits" +
                        " chimiques ménagers, les peintures, les solvants, etc. Ils doivent être " +
                        "éliminés de manière spécifique pour éviter toute contamination. ";

                String comment = "Nous vous déconseillons de prendre action tous seuls , si vous estimez que le déchet est dangereux " +
                        "veuillez informer les services adéquats.\n" +
                        "Signalez le sur l'application\n" +
                        "Voici quelques numéros à contacter :\n" +
                        "Service de nettoyage : 06583840284";

                return new GuideToxique(infoGen, comment);
            }
            default : throw new Throwable("not made");
        }
    }
}
