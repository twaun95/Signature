# Signature
(스토어 링크)
(이미지)
|  1 | 2 |
|:----------:|:----------:|
| 좌우 스크롤 | 좌우 상하 스크롤 |


# Description
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

# Environment
- Android Studio Chipmunk 2021.2.1
- minSdkVersion 26
- targetSdkVersion 32
- Test Device - Galaxy S10e





