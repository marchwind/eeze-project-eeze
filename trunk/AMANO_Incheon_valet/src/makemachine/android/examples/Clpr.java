package makemachine.android.examples;


public class Clpr {
    static
    {
        System.loadLibrary("LprSub");               
        System.loadLibrary("CppLpr");         
        
    }

    public native byte[] libProc(int[] img, int w, int h, byte[] Limg, int[] WH);        // native �Լ� libProc ����

    public byte[] proc(int[] img, int w, int h, byte[] Limg, int[] WH)                     // native �Լ��� ȣ���ϴ� �ɹ� �޼���
    {
        return libProc(img, w, h, Limg, WH);
        
    }
    public native String getString(String str);
    public native String getFileRead();
     
     

}
