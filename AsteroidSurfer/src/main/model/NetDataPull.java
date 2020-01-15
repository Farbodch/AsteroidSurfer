package main.model;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class NetDataPull {

    private BufferedReader br;

    public NetDataPull() {
        br = null;
    }

    public StringBuilder dataRequest() throws MalformedURLException, IOException{
        try {
            String apikey = "faadaf98c2a91c2cec8483be7a73bffe"; //fill this in with the API key they email you
            String londonweatherquery = "https://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=";
            String theURL = londonweatherquery+apikey;
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
//---THIS
            return sb;

        } finally {

            if (this.br != null) {
                this.br.close();
            }
        }
    }
}
