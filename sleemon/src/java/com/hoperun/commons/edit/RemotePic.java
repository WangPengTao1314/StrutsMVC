package com.hoperun.commons.edit;

import java.io.*;
import java.net.*;

public class RemotePic
{

    public RemotePic()
    {
    }

    public boolean download()
    {
        String s;
        String s1;
        s = picurl;
        s1 = savepath;
        URLConnection urlconnection;
        try{
                URL url = new URL(s);
                urlconnection = url.openConnection();
                urlconnection.connect();
                HttpURLConnection httpurlconnection = (HttpURLConnection)urlconnection;
                int i = httpurlconnection.getResponseCode();
                if(i == 200){
                    System.out.println("Connect to " + s + " failed,return code:" + i);
                    return false;
                }
                InputStream inputstream;
                FileOutputStream fileoutputstream;
                int j = urlconnection.getContentLength();
                inputstream = urlconnection.getInputStream();
                byte abyte0[] = new byte[1024];
                java.io.File file = new java.io.File(s1);
                if(!file.exists())
                    file.createNewFile();
                fileoutputstream = new FileOutputStream(file);
                int k = 0;
                if(j < 0)
                {
                    while(k > -1) 
                    {
                        k = inputstream.read(abyte0);
                        if(k > 0)
                            fileoutputstream.write(abyte0, 0, k);
                    }
                    return false;////?
                }
                int l;
                for(l = 0; l < j && k != -1;)
                {
                    k = inputstream.read(abyte0);
                    if(k > 0)
                    {
                        fileoutputstream.write(abyte0, 0, k);
                        l += k;
                    }
                }
        
                if(l >= j){
                    System.out.println("download error");
                    inputstream.close();
                    fileoutputstream.close();
                    file.delete();
                    return false;
                }
                fileoutputstream.flush();
                fileoutputstream.close();
                inputstream.close();
            }catch( Exception exception){
                exception.printStackTrace();
                return false;
            }
        
        return true;
    }

    public String picurl;
    public String savepath;


}
