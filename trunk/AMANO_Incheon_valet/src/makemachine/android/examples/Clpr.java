package makemachine.android.examples;


public class Clpr {
    static
    {
        System.loadLibrary("LprSub");         // 라이브러리를 로드하는 static 함수      
        System.loadLibrary("CppLpr");         // 라이브러리를 로드하는 static 함수
    }

    public native byte[] libProc(int[] img, int w, int h, byte[] Limg, int[] WH, int lprType);        // native 함수 libProc 선언

    public byte[] proc(int[] img, int w, int h, byte[] Limg, int[] WH, int lprType)                     // native 함수를 호출하는 맴버 메서드
    {
        return libProc(img, w, h, Limg, WH, lprType);
        
    }
    
    public native String getString(String str);
    public native String getFileRead();
    
}
