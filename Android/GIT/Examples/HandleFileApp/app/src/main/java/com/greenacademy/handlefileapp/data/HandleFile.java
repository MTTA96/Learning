package com.greenacademy.handlefileapp.data;

import android.app.Activity;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import com.greenacademy.handlefileapp.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by GIT on 3/25/2017.
 */

public class HandleFile {
    Activity display;
    TextView tvError;

    final static String ERROR_FILE = "Xem lại tên file";
    final static String ERROR_DISK = "Lỗi truy xuất ổ đĩa";
    final static String DELETE_FILE = "Bạn đã xóa thành công";

    public HandleFile(Activity display) {
        this.display = display;
        tvError = (TextView) display.findViewById(R.id.tvThongBaoLoi);
    }

    //Ghi file
    //Bước 1: mở luồng để truy xuất file
    //Bước 2: ghi dữ liệu xuống
    //Bước 3: Đóng luồng
    public void writeFileApp(String nameFile, String content){
        //Thong qua class OutputStramWriter
        try {
            OutputStreamWriter out = new OutputStreamWriter(display.openFileOutput(nameFile, 0));
            out.write(content);
            out.close(); //Đóng luồng
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            tvError.setText(ERROR_FILE);
        } catch (IOException e) {
            //e.printStackTrace();
            tvError.setText(ERROR_DISK);
        }
    }

    //Đọc file
    //Bước 1: mở luồng truy xuất file
    //Bước 2: đọc dữ liệu lên (2 dạng):
    //        + Đọc theo dạng nhị phân
    //        + Đọc theo dạng chuỗi (text - hết hàng)
    //Bước 3: Đóng luồng
    public String readFileApp(String nameFile){
        String result = "";
        try {
            InputStream in = display.openFileInput(nameFile);
            if(in == null){
                tvError.setText(ERROR_FILE);
                return "";
            }

            //Đọc theo dạng text
            InputStreamReader temp = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(temp);
            //Tạo biến để đọc từng hàng
            String strLine;
            //Tạo biến lưu kết quả trả về
            StringBuffer strBuffer = new StringBuffer();   //Nếu khai báo String khi nối chữ sẽ lâu hơn StringBuffer
            while((strLine = bufferedReader.readLine()) != null){
                strBuffer.append(strLine + "\n"); //nối chuỗi thông qua append
            }
            in.close();
            result = strBuffer.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            tvError.setText(ERROR_FILE);
        } catch (IOException e) {
            e.printStackTrace();
            tvError.setText(ERROR_DISK);
        }
        return result;
    }

    //Xóa file
    public void deleteFileApp(String nameFile){
        display.deleteFile(nameFile);
        tvError.setText(DELETE_FILE);
    }

    //Đọc file raw
    public String readFileRaw(){
        String result = "";
        InputStream in = display.getResources().openRawResource(R.raw.quehuong);
        if(in != null){
            //Đọc theo dạng line
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            //Tạo biến để đọc từng hàng
            String strLine = "";
            //Lấy kết quả trả về
            StringBuffer stringBuffer = new StringBuffer();
            try {
                while ((strLine = reader.readLine()) != null){
                    stringBuffer.append(strLine + "\n");
                }
                result  = stringBuffer.toString();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //Ghi file
    //Bước 0: Xác định đường dẫn của file
    //Bước 1: mở luồng để truy xuất file
    //Bước 2: ghi dữ liệu xuống
    //Bước 3: Đóng luồng
    public String getPathExternal(){
        return Environment.getExternalStorageDirectory().getPath();
    }

    //Đọc ghi bộ nhớ ngoài
    public void writeFileExternal(String nameFile, String content){
        String path = getPathExternal();
        Log.v("path", path);
        path = path + "/" + nameFile; // nếu dùng "\" phải dùng \\

        //1 cách mở luồng ghi file
        PrintWriter outfile = null;
        try {
            outfile = new PrintWriter(new FileWriter(path));
            outfile.println(content); //println, ln để xuống hàng
            outfile.close();
        } catch (IOException e) {
            tvError.setText(ERROR_DISK);
        }
    }

    public String readFileExternal(String nameFile){
        String result = "";
        String path = getPathExternal() + "/" + nameFile;
        //Tương tự InputStream
        try {
            Scanner infile = new Scanner(new FileReader(path));
            String strLine = "";
            StringBuffer stringBuffer = new StringBuffer();
            while (infile.hasNext()){
                strLine = infile.nextLine();
                stringBuffer.append(strLine + " /n");
            }
            result = stringBuffer.toString();
            infile.close();
        } catch (FileNotFoundException e) {
            tvError.setText(ERROR_FILE);
        }
        return result;
    }

    public  boolean deleteExternal(String nameFile){
        //Trả về true -> xóa thành công
        String path = getPathExternal() + "/" + nameFile;
        File file = new File(path);
        return file.delete();
    }
}
