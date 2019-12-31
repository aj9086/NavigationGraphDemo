package aj.navigationcomponents.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class UserInfo() : Serializable {

    var email: String =""
    var password:String =""
    var name:String =""
    var dob:String =""
    var phnNum: String =""
    var address: String =""

}