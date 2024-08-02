import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.classmanagementsystem.data.database.DataStoreManager
import com.example.classmanagementsystem.data.database.ProfileViewModel

class ProfileViewModelFactory(private val dataStoreManager: DataStoreManager) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(dataStoreManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
