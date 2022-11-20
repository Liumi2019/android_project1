#include <jni.h>
#include <string>
#include "printStr.h"

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_app_MainActivity_get(JNIEnv* env, jobject obj) {
    Str str;

    return env->NewStringUTF(str.printStrP().c_str());

}


