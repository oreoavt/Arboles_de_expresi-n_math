import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import org.opencv.highgui.HighGui;

public class Main{
    public static void main(String[] args){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        VideoCapture capture = new VideoCapture(0);

        if(!capture.isOpened()){
            System.out.println("No se pudo abrir la cámara");
            return;
        }
         HighGui.namedWindow("Cámara", HighGui.WINDOW_AUTOSIZE);

        CascadeClassifier faceCascade = new CascadeClassifier();
        faceCascade.load("C:\\\\Users\\\\User\\\\Documents\\\\Calculadora-con-Arboles-Binarios\\\\los_papus\\\\furro\\\\src\\\\haarcascade_frontalface_default.xml");

        while(true){
            Mat frame = new Mat();
            capture.read(frame);

            if (!frame.empty()){
                Mat grayFrame = new Mat();
                Imgproc.cvtColor(frame,grayFrame, Imgproc.COLOR_BGR2GRAY);

                MatOfRect faces = new MatOfRect();
                faceCascade.detectMultiScale(grayFrame, faces);

                for (Rect rect : faces.toArray()){
                    Imgproc.rectangle(frame, rect.tl(), rect.br(), new Scalar(0,255,0),3);

                }
                HighGui.imshow("Cámara", frame);
            }else{
                System.out.println("No se pudo obtener el fotograma");
                break;
            }
            if (HighGui.waitKey(30)>=0)
                break;
        }
        capture.release();
        HighGui.destroyAllWindows();
    }
}