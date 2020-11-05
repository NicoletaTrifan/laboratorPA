import cv2
import numpy as np

cam = cv2.VideoCapture('Lane Detection Test Video 01.mp4')

while True:
    ret, frame = cam.read()

    if ret is False:
        break

    cv2.imshow('Original', frame)
    # print(frame.shape[0]/2)
    # cv2.resize(frame,((int(frame.shape[0]/2)), int(frame.shape[1]/2)))
    # print(frame.shape)
    scale_percent = 30
    width = int(frame.shape[1] * scale_percent / 100)
    height = int(frame.shape[0] * scale_percent / 100)
    dim = (width, height)
    resized = cv2.resize(frame, dim)
    cv2.imshow('Original resized', resized)
    # change color
    white_gray = cv2.cvtColor(resized, cv2.COLOR_BGR2GRAY)
    cv2.imshow('WG', white_gray)
    # ex4 create a black frame
    separate_frame = np.zeros((height, width), dtype=np.uint8)
    upper_left = (int(width * 0.4), int(height * 0.75))
    upper_right = (int(width * 0.6), int(height * 0.75))
    lower_left = (0, height)
    lower_right = (width, height)
    trapezoid_array = np.array([ upper_right, upper_left,lower_left, lower_right], dtype=np.int32)
    cv2.fillConvexPoly(separate_frame, trapezoid_array, 1)
    cv2.imshow("Trapezoid", separate_frame*255)
    multiplied = separate_frame * white_gray
    cv2.imshow("Road", multiplied)
    screen_upper_left = (0,0)
    screen_upper_right = (width, 0)
    screen_lower_left = lower_left
    screen_lower_right = lower_right
    trapezoid_array=np.float32(trapezoid_array)
    #"magical-matrix"
    trapezoid_bounds = np.array([screen_upper_right, screen_upper_left, screen_lower_left, screen_lower_right], dtype=np.float32)
    magical_matrix = cv2.getPerspectiveTransform(trapezoid_array, trapezoid_bounds)
    stretched = cv2.warpPerspective(multiplied,magical_matrix,(width, height))
    cv2.imshow('top-down', stretched)
    #ex 6
    blurred_image = cv2.blur(stretched, ksize=(5,5))
    cv2.imshow('blur', blurred_image)
    #ex 7
    sobel_vertical = np.float32([[-1, -2, -1],
                               [0, 0, 0],
                               [+1, +2, +1]])
    sobel_horizontal = np.transpose(sobel_vertical)
    blurred_image_as_float32_v = np.float32(blurred_image)
    blurred_image_as_float32_h = np.float32(blurred_image)
    sobel_v = cv2.filter2D(blurred_image_as_float32_v, -1,sobel_vertical)
    sobel_h = cv2.filter2D(blurred_image_as_float32_h, -1, sobel_horizontal)
    #cv2.imshow("Sobel-vertical", cv2.convertScaleAbs(sobel_v))
    #cv2.imshow("Sobel-orizontal", cv2.convertScaleAbs(sobel_h))
    #combine images
    sobel_compund = np.sqrt((sobel_v**2)+(sobel_h**2))
    sobel_final = cv2.convertScaleAbs(sobel_compund)
    cv2.imshow('Sobel', sobel_final)
    #ex8 binarize frame
    threshold = int(255/2)
    #print(sobel_final)
    ret, thresh = cv2.threshold(sobel_final, threshold, 255, cv2.THRESH_BINARY)
    cv2.imshow('Binarized', thresh)

    binarized_copy = thresh.copy()
    binarized_copy[0:int(height), 0:int(width*0.05)] = 0
    binarized_copy[0:int(height), int(width * 0.85):width] = 0
    cv2.imshow('Cleared', binarized_copy)

    #ex 9b
    first_half = binarized_copy[0:height, 0:int(width/2)]
    second_half = binarized_copy[0:height, int(width/2):width]

    left_xs = list()
    left_ys = list()

    right_xs = list()
    right_ys = list()

    for index, x in np.ndenumerate(first_half):
        if x >= threshold:
            #ni se returneaza tuplul y x
            left_xs.append(index[1])
            left_ys.append(index[0])

    for index, x in np.ndenumerate(second_half):
        if x >= threshold:
            right_xs.append(index[1])
            right_ys.append(index[0])

    # #ex10
    line_left = np.polyfit(left_xs, left_ys, deg=1)
    line_right = np.polyfit(right_xs, right_ys, deg=1)
    
    left_top_y =0
    left_top_x = int ((left_top_y - line_left[1])/line_left[0])

    left_bottom_y = height
    left_bottom_x = int ((left_bottom_y - line_left[1])/line_left[0])

    right_top_x = 0
    right_top_y = int((right_top_x - line_right[1])/line_right[0])

    right_bottom_y = height
    right_bottom_x = int((right_bottom_y - line_right[1])/line_right[0])

   #lined_frame = cv2.line(binarized_copy, (left_top_x, left_top_y), (left_bottom_x, left_bottom_y), (200,0,0), 5)
    lined_frame = cv2.line(binarized_copy, (right_top_x, right_top_y), (right_bottom_x, right_bottom_y), (100, 0, 0), 5)
    cv2.imshow('Lined', lined_frame)
    #print(left_xs)
    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

cam.release()
cv2.destroyAllWindows()
