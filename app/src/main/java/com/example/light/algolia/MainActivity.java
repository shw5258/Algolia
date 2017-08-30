package com.example.light.algolia;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.algolia.instantsearch.helpers.InstantSearch;
import com.algolia.instantsearch.helpers.Searcher;
import com.algolia.instantsearch.ui.views.Hits;
import com.algolia.instantsearch.utils.ItemClickSupport;
import com.algolia.search.saas.AlgoliaException;
import com.algolia.search.saas.Client;
import com.algolia.search.saas.CompletionHandler;
import com.algolia.search.saas.Index;
import com.algolia.search.saas.Query;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Client mClient;
    private ListView mListView;
    private ArrayList<String> mArrayString;
    private Searcher mSearcher;
    public final static String EXTRA_MESSAGE = "com.example.light.algolia.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.hitRecycler);
        Hits hits = (Hits) recyclerView;
        hits.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView recyclerView, int position, View v) {
                String chosenString = ((TextView)v.findViewById(R.id.placeId)).getText().toString();
                Intent startIntent = new Intent(MainActivity.this, DetailActivity.class);
                startIntent.putExtra(EXTRA_MESSAGE, chosenString);
                startActivity(startIntent);
            }
        });
        mSearcher = Searcher.create("HSCD77G6HK", "7f34fc74ed47b1c57110cdb1ba5eabde","place");
//        mClient = new Client("HSCD77G6HK", "7f34fc74ed47b1c57110cdb1ba5eabde");
//        Button searchButton = (Button) findViewById(R.id.retrieve_button);
//        mListView = (ListView) findViewById(R.id.listview);
//        mArrayString = new ArrayList<>();
//        searchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                search("seoul");
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        // link the Searcher to the UI
        new InstantSearch(this, menu, R.id.action_search, mSearcher);
                //.search(); //Show results for empty query on startup

        // Retrieve the SearchView and plug it into SearchManager
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

//    public void search(String querying){
//        Index index = mClient.getIndex("contacts");
//        Query query = new Query(querying)
//                .setAttributesToRetrieve("city", "name")
//                .setHitsPerPage(50);
//        index.searchAsync(query, new CompletionHandler() {
//            @Override
//            public void requestCompleted(JSONObject content, AlgoliaException error) {
//
//                try {
//                    Log.d("MainActivity", content.getJSONArray("hits").toString());
//                    JSONArray array = content.getJSONArray("hits");
//                    for (int i=0 ; i < array.length();i++) {
//                        JSONObject object = array.getJSONObject(i);
//                        String string = object.getString("name");
//                        mArrayString.add(string);
//                    }
//                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, mArrayString);
//                    mListView.setAdapter(adapter);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
}
