package makemachine.android.examples;


public class Clpr {
    static
    {
        System.loadLibrary("LprSub");         // ���̺귯���� �ε��ϴ� static �Լ�      
        System.loadLibrary("CppLpr");         // ���̺귯���� �ε��ϴ� static �Լ�
    }

    public native byte[] libProc(int[] img, int w, int h, byte[] Limg, int[] WH, int lprType);        // native �Լ� libProc ����

    public byte[] proc(int[] img, int w, int h, byte[] Limg, int[] WH, int lprType)                     // native �Լ��� ȣ���ϴ� �ɹ� �޼���
    {
        return libProc(img, w, h, Limg, WH, lprType);
        
    }
    
    public native String getString(String str);
    public native String getFileRead();
    
}
