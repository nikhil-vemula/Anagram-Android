package google.cswithandrod.anagram;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    static Map<String,List<String>> map=new HashMap<String,List<String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AssetManager assetManager=getAssets();
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(assetManager.open("dictionary.txt")));
            String s;
            while((s=br.readLine())!=null)
                addToMap(s.toLowerCase());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void find(View view)
    {
        TextView textView= (TextView) findViewById(R.id.textView);
        EditText editText=(EditText) findViewById(R.id.editText);
        ListView listView= (ListView) findViewById(R.id.listview);
        String words="";
        String word=editText.getText().toString();
        char[] ch=word.toCharArray();
        Arrays.sort(ch);
        String key=new String(ch);
        if(map.containsKey(key))
        {
            textView.setText("Anagrams are:");
            List<String> list=map.get(key);
            listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list));
            listView.setVisibility(View.VISIBLE);
            /*Iterator itr=list.iterator();
            while(itr.hasNext())
            {
                words+=itr.next()+"\n";
            }
            textView.setText("Anagrams are:"+"\n"+words);*/
        }
        else
        {
            textView.setText("Not Possible");
            listView.setVisibility(View.INVISIBLE);
        }
    }
    static void addToMap(String word)
    {
        char[] ch=word.toCharArray();
        Arrays.sort(ch);
        String key=new String(ch);
        if(map.containsKey(key))
        {
            List<String> list=map.get(key);
            list.add(word);
        }
        else
        {
            List<String> list=new ArrayList<String>();
            list.add(word);
            map.put(key,list);
        }
    }
}
