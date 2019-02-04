import java.util.*;

public class Zamowienie {

	Magazynier nadzorujacy;
	Klient posiadajacy;
	Faktura okreslajace;
	Collection<ZamowienieCzesc> zamowienieCzesc;
	Real cena;
	Status status;
	Integer nrZamowienia;
	Boolean czyNaRaty;

}