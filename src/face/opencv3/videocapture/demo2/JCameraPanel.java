/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package face.opencv3.videocapture.demo2;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.HOGDescriptor;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

/**
 * 基于OpenCV实现的视频图像采集面板
 *
 * @author wcss
 */
public class JCameraPanel extends javax.swing.JPanel implements Runnable {

    /**
     * 背景图片
     */
    public BufferedImage backgroundImg;

    /**
     * 摄像头对象
     */
    protected VideoCapture cameraObj = null;

    /**
     * 摄像头序号
     */
    protected int cameraIndex = 0;

    /**
     * 正在运行
     */
    protected boolean isRunning = false;

    /**
     * 守护进程对象
     */
    protected Thread threadObj = null;

    /**
     * Creates new form JCameraPanel
     */
    public JCameraPanel() {
        initComponents();

        try {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        } catch (Exception ex) {
            System.out.println(ex.toString());
            throw ex;
        }
    }

    /**
     * 将OpenCV的图像转换为Java的BufferedImage对象以便显示在窗体中
     *
     * @param mat
     * @return
     */
    public static BufferedImage convertToImage(Mat mat) {
        int dataSize = mat.cols() * mat.rows() * (int) mat.elemSize();
        byte[] data = new byte[dataSize];
        mat.get(0, 0, data);
        int type = mat.channels() == 1
                ? BufferedImage.TYPE_BYTE_GRAY : BufferedImage.TYPE_3BYTE_BGR;

        if (type == BufferedImage.TYPE_3BYTE_BGR) {
            for (int i = 0; i < dataSize; i += 3) {
                byte blue = data[i + 0];
                data[i + 0] = data[i + 2];
                data[i + 2] = blue;
            }
        }
        BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
        image.getRaster().setDataElements(0, 0, mat.cols(), mat.rows(), data);

        return image;
    }

    /**
     * 绘图函数
     *
     * @param g
     */
    protected void paintComponent(Graphics g) {
        if (backgroundImg != null) {
            g.drawImage(backgroundImg, 0, 0, backgroundImg.getWidth(), backgroundImg.getHeight(), this);
        }
    }

    /**
     * 开启服务
     *
     * @param cameraIndex
     */
    public void start(int cameraIndex) throws Exception {
        if (threadObj == null) {
            this.cameraIndex = cameraIndex;
            this.isRunning = true;
            
            threadObj = new Thread(this);
            threadObj.setDaemon(true);
            threadObj.start();
        }else
        {
            throw new Exception("Camera Is Busy!");
        }
    }

    /**
     * 结束服务
     */
    public void stop() {
        this.isRunning = false;
        this.threadObj = null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 717, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 422, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void run() {
        while (isRunning) {

        }

        System.out.println("对不起，摄像头守护进程退出！");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
