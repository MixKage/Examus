package my.app.examus


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.tasks.Task

class Tutor : AppCompatActivity() {
    private val STORAGE_REQUEST_CODE = 101
    private val RC_SIGN_IN = 0


    var APPLICATION_NAME = "Google Docs API Java Quickstart"
//    var JSON_FACTORY: JsonFactory = JacksonFactory.getDefaultInstance()
    var TOKENS_DIRECTORY_PATH = "tokens"

    var DOCUMENT_ID = "195j9eDD3ccgjQRttHhJPymLJUCOUjs-jmwTrekvdjFE"
//    val SCOPES: List<String> = Collections.singletonList(DocsScopes.DOCUMENTS_READONLY)
    val CREDENTIALS_FILE_PATH = "/credentials.json"

    lateinit var mGoogleApiClient: GoogleApiClient

    lateinit var text: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutor)
        text = findViewById(R.id.text)

//РАБОТА С РАЗРЕШЕНИЯМИ
        val permissionStatus1 =
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        val permissionStatus2 =
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        Log.d("PER1", permissionStatus1.toString())
        Log.d("PER1", permissionStatus2.toString())
        setupPermissions()
        //Перенос на следующий экран
        startActivity(Intent(this, Login::class.java))
        //var DriveQuickstart = DriveQuickstart()
//TODO НЕ ЗАБЫТЬ
//        val fileId = "195j9eDD3ccgjQRttHhJPymLJUCOUjs-jmwTrekvdjFE"
//        val outputStream: OutputStream = ByteArrayOutputStream()
//        driveService.files().export(fileId, "application/html")
//            .executeMediaAndDownloadTo(outputStream)
        //GOOGLE AUTH

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken("K6HbFWO6X_DhISS1ryGbcRfi")
//            .requestEmail()
//            .build()

//        mGoogleApiClient = GoogleApiClient.Builder(this).enableAutoManage(this,
//            OnConnectionFailedListener()
//        ).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build()

        var mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

//        signIn()
        val sign_in_button =
            findViewById<com.google.android.gms.common.SignInButton>(R.id.sign_in_button)
        sign_in_button?.setOnClickListener()
        {
            signIn(mGoogleSignInClient)
        }
//        val account = GoogleSignIn.getLastSignedInAccount(this)

//        if (account == null) {
//            val signInIntent = mGoogleSignInClient.signInIntent
//            startActivityForResult(signInIntent, RC_SIGN_IN)
//        }


//        val HTTP_TRANSPORT = newTrustedTransport()
//        val service = Docs.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
//            .setApplicationName(APPLICATION_NAME)
//            .build()
//
//        // Prints the title of the requested doc:
//        // https://docs.google.com/document/d/195j9eDD3ccgjQRttHhJPymLJUCOUjs-jmwTrekvdjFE/edit
//        val response: Document = service.documents()[DOCUMENT_ID].execute()
//        val title: String = response.title
//        System.out.printf("The title of the doc is: %s\n", title)
//        Log.d("PER1", title)

//        Перенос на главный экран
//        startActivity(Intent(this, NewMain::class.java))
    }


//private fun signIn(){
//        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
//        startActivityForResult(signInIntent,RC_SIGN_IN)
//    }


    private fun signIn(googleSignInClient: GoogleSignInClient) {
        val account = GoogleSignIn.getLastSignedInAccount(this)
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//            try {
//                // Google Sign In was successful, authenticate with Firebase
//                val account = task.getResult(ApiException::class.java)!!
//                Log.d("PER1", "firebaseAuthWithGoogle:" + account.id)
//            } catch (e: ApiException) {
//                // Google Sign In failed, update UI appropriately
//                Log.w("PER1", "Google sign in failed", e)
//            }
//        }
//    }

//    @Throws(IOException::class)
//    private fun getCredentials(HTTP_TRANSPORT: NetHttpTransport, userId: String): Credential? {
//        // Load client secrets.
//        val `in`: InputStream =
//            Tutor::class.java.getResourceAsStream(CREDENTIALS_FILE_PATH)
//                ?: throw FileNotFoundException("Resource not found: $CREDENTIALS_FILE_PATH")
//        val clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, InputStreamReader(`in`))
//
//        // Build flow and trigger user authorization request.
//
//        // Build flow and trigger user authorization request.
//        val flow = GoogleAuthorizationCodeFlow.Builder(
//            HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES
//        )
//            .setDataStoreFactory(FileDataStoreFactory(File(TOKENS_DIRECTORY_PATH)))
//            .setAccessType("offline")
//            .build()
//        val receiver = LocalServerReceiver.Builder().setPort(8888).build()
//        return AuthorizationCodeInstalledApp(flow, receiver).authorize(userId)
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
//            val HTTP_TRANSPORT = com.google.api.client.http.javanet.NetHttpTransport()
//            val account: GoogleSignInAccount = completedTask.getResult(ApiException::class.java)
//            getCredentials(HTTP_TRANSPORT, account.id)
            // Signed in successfully, show authenticated UI.
            val account = completedTask.getResult(ApiException::class.java)
            text.text = account.displayName
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("sign", "signInResult:failed code=" + e.statusCode)
        }
    }

//    private fun setupPermissions() {
//        val permission = ContextCompat.checkSelfPermission(this,
//            Manifest.permission.RECORD_AUDIO)
//
//        if (permission != PackageManager.PERMISSION_GRANTED) {
//            Log.i("PER1", "Permission to record denied")
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                    Manifest.permission.RECORD_AUDIO)) {
//                val builder = AlertDialog.Builder(this)
//                builder.setMessage("Permission to access the microphone is required for this app to record audio.")
//                        .setTitle("Permission required")
//
//                            builder.setPositiveButton("OK"
//                            ) { dialog, id ->
//                        Log.i("PER1", "Clicked")
//                        makeRequest()
//                    }
//
//                    val dialog = builder.create()
//                dialog.show()
//            } else {
//                makeRequest()
//            }
//        }
//    }

    private fun setupPermissions() {
        val permission1 = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val permission2 = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        if (permission1 != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                STORAGE_REQUEST_CODE
            )
        } else
            Log.d("PER1", "Permission to WRITE NO denied")
        if (permission1 != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                STORAGE_REQUEST_CODE
            )
        } else
            Log.d("PER1", "Permission to READ NO denied")
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            STORAGE_REQUEST_CODE -> {

                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                    Log.d("PER1", "Permission has been denied by user")
                } else {
                    Log.d("PER1", "Permission has been granted by user")
                }
            }
        }
    }
}
