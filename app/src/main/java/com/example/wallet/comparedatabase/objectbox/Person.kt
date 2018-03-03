package com.example.wallet.comparedatabase.objectbox

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.annotation.NameInDb

/**
 * Created by glebkalinichenko on 03.03.18.
 */
@Entity
class Person(@Id var id: Long, @NameInDb("first_name") var firstName: String,
             @NameInDb("last_name") var lastName: String, var age: Int)