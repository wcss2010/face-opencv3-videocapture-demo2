/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package face.opencv3.videocapture.demo2;

import org.opencv.core.Mat;

/**
 * 用于对摄像头的图片进行解析
 * @author wcss
 */
public interface IImageDetect {
    /**
     * 检查图片
     * @param source
     * @return 
     */
    Mat detect(Mat source);
}