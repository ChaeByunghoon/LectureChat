package kr.co.hoonki.lecturechat.Chat;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import kr.co.hoonki.lecturechat.R;
import lombok.NonNull;

public class ChatCreateActivity extends AppCompatActivity {

    private Button btnCancel, btnCreate, btnPicture;
    private EditText txtTitle, txtPicture;

    private final int GALLERY_REQUEST = 10;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseStorage storage;
    private StorageReference storageRef;

    ProgressDialog progressDialog;
    UploadTask uploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_create);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

        btnCancel = findViewById(R.id.btnCancel_cc);
        btnCreate = findViewById(R.id.btnCreate_cc);
        btnPicture = findViewById(R.id.btnPicture_cc);

        txtTitle = findViewById(R.id.etTitle_cc);
        txtPicture = findViewById(R.id.etPicture_cc);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtTitle.getText().equals("")) return;

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Room");
                String roomKey = myRef.push().getKey();
                myRef = myRef.child(roomKey);

                Map<String, String> userData = new HashMap<>();
                userData.put("roomName", txtTitle.getText().toString());
                userData.put("roomImage", txtPicture.getText().toString());
                userData.put("createDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                userData.put("updateDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                myRef.setValue(userData);

                myRef = database.getReference("User/" + firebaseUser.getUid() + "/chatRoomList/" + roomKey);
                myRef.setValue(true);

                setResult(RESULT_OK, getIntent());

                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED, getIntent());
                finish();
            }
        });

        btnPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case GALLERY_REQUEST:
                    Uri selectedImage = data.getData();

                    storageRef = storage.getReference().child("roomImages/" + firebaseUser.getUid() + "/" + selectedImage.getLastPathSegment());

                    //creating and showing progress dialog
                    progressDialog = new ProgressDialog(this);
                    progressDialog.setMax(100);
                    progressDialog.setMessage("Uploading...");
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    //starting upload
                    uploadTask = storageRef.putFile(selectedImage);
                    // Observe state change events such as progress, pause, and resume
                    uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            //sets and increments value of progressbar
                            progressDialog.incrementProgressBy((int) progress);
                        }
                    });
                    // Register observers to listen for when the download is done or if it fails
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            Log.e("ChatCreateActivity", "Error in uploading!");
                            progressDialog.dismiss();
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                            Uri downloadUrl = taskSnapshot.getDownloadUrl();
                            Log.d("ChatCreateActivity", "Uploa successful!");
                            progressDialog.dismiss();
                            //showing the uploaded image in ImageView using the download url
                            txtPicture.setText(downloadUrl.toString());
                        }
                    });

                    break;
            }
        }
    }
}
