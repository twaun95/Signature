# Signature

|  배경 색상 변경 | 펜 색상 변경 | 펜 굵기 |
|:----------:|:----------:|:----------:|
| <video src="https://user-images.githubusercontent.com/43190509/194696716-9aad7146-2ea4-438f-a1df-c3abcb468518.mp4" width="200" height="400" /> | <video src="https://user-images.githubusercontent.com/43190509/194696790-907b5ba7-d5ca-441f-ac28-31082eb75db0.mp4" width="200" height="400"/> | <video src="https://user-images.githubusercontent.com/43190509/194696797-4c254abd-4394-4582-bc24-fd909b6e6f7a.mp4" width="200" height="400" /> |

| 지우개 | 초기화 | 저장 |
|:----------:|:----------:|:----------:|
| <video src="https://user-images.githubusercontent.com/43190509/194696804-35654224-9de6-4e36-a652-b0674ef6cff9.mp4" width="200" height="400"/> | <video src="https://user-images.githubusercontent.com/43190509/194696813-8beaa2c1-63b5-47dc-b766-01de2f2d2008.mp4" width="200" height="400" /> | <video src="https://user-images.githubusercontent.com/43190509/194696816-98f4c7a4-9fc0-40a7-beff-ba07c8355617.mp4" width="200" height="400"/> |


# Description

[🔗 구글 스토어 링크](https://play.google.com/store/apps/details?id=com.twaun95.signature)

손가락으로 쉽고 빠르게 그림을 그려보세요.

문득 영감이 떠올랐을 때 재빠르게 그린 후에 사진첩에 저장하세요.

여러 도구를 활용해서 그림을 그려보세요.
- 배경 색상 변경
- 펜 색상 변경
- 펜 굵기 변경
- 지우개 굵기 변경
- 초기화
- 갤러리 저장

# Structure

<pre>
.app/
  &boxvr;&boxh;&boxh; common
  &boxur;&boxh;&boxh; di (koin)
  &boxv;      &boxvr;&boxh;&boxh; HandlerModule
  &boxv;      &boxur;&boxh;&boxh; ViewModelModule
  &boxur;&boxh;&boxh; presentation
        &boxvr;&boxh;&boxh; extensions
        &boxvr;&boxh;&boxh; model
        &boxvr;&boxh;&boxh; extensions
        &boxur;&boxh;&boxh; handler
        &boxv;      &boxur;&boxh;&boxh; ImageSaveHandler (이미지 갤러리 저장)
        &boxur;&boxh;&boxh; utils
        &boxv;      &boxvr;&boxh;&boxh; dialog
        &boxv;      &boxvr;&boxh;&boxh; drawable
        &boxv;      &boxur;&boxh;&boxh; toast
        &boxur;&boxh;&boxh; view (Custom View)
        &boxv;      &boxvr;&boxh;&boxh; DrawingView (화면에 그려지는 뷰)
        &boxv;      &boxvr;&boxh;&boxh; EraserPreView (지우개 두께 조절 미리보기)
        &boxv;      &boxur;&boxh;&boxh; PenPreView (펜 두께 조절 미리보기)
        &boxur;&boxh;&boxh; ui
              &boxur;&boxh;&boxh; main (Activity, ViewModel)
  
</pre>


# Skills
- Kotlin
- Koin
- ViewModel
- Jetpack
- DataBinding
- LiveData
- CustomView
- FireBase(App Distribution, Crashlytics)

# Environment
- Android Studio Chipmunk 2021.2.1
- minSdkVersion 26
- targetSdkVersion 32
- Test Device - Galaxy S10e





