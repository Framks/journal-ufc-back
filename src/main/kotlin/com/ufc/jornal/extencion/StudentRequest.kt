package com.ufc.jornal.extencion

import com.ufc.jornal.domain.enums.EducationLevel
import com.ufc.jornal.rest.request.student.StudentRequest

fun StudentRequest.isSuperiorLevel() = this.educationLevel == EducationLevel.UNDERGRADUATE