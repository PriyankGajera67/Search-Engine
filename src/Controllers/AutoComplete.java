package Controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AutoComplete {

	public static List<String> getAutoCompleteData(String query) {

		List<String> matchedList = new ArrayList<String>();
		Collection<String> prefixColumn = CreateTST.trieObject.autoComplete(query.toLowerCase());

		matchedList.addAll(prefixColumn);

		return matchedList;
	}

}
