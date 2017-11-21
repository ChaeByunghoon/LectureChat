package kr.co.hoonki.lecturechat.Chat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.icons.MaterialDrawerFont;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.hoonki.lecturechat.ChatBoardActivity;
import kr.co.hoonki.lecturechat.LoginActivity;
import kr.co.hoonki.lecturechat.R;

public class ChatListActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mRef;
    private RecyclerView recyclerView;
    private ChatRoomAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private FloatingActionMenu materialDesignFAM;
    private FloatingActionButton btnChatRoomAdd;

    private TextView txtTitle;
    private Button btnSearch;

    private final String TAG = "ChatListActivity";
    private final int CREATE_REQUEST = 101;
    private final int SEARCH_REQUEST = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        ButterKnife.bind(this);

        mFirebaseAuth = FirebaseAuth.getInstance();
        if (!checkLogin()) return;

        mFirebaseDatabase = FirebaseDatabase.getInstance();

        recyclerView = findViewById(R.id.rv_chatList_chatList);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // 채팅 정보 가져옴
        getChatRoomData();

       materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
       btnChatRoomAdd = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
       btnChatRoomAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatListActivity.this, ChatCreateActivity.class);
                startActivityForResult(intent, CREATE_REQUEST);
            }
        });

        btnSearch = (Button) findViewById(R.id.btn_chatList_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatListActivity.this, ChatRoomSearchActivity.class);
                startActivityForResult(intent, SEARCH_REQUEST);
            }
        });

        DrawerImageLoader.init(new AbstractDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder) {
                Picasso.with(imageView.getContext()).load(uri).placeholder(placeholder).into(imageView);
            }

            @Override
            public void cancel(ImageView imageView) {
                Picasso.with(imageView.getContext()).cancelRequest(imageView);
            }

            /*
            @Override
            public Drawable placeholder(Context ctx) {
                return super.placeholder(ctx);
            }

            @Override
            public Drawable placeholder(Context ctx, String tag) {
                return super.placeholder(ctx, tag);
            }
            */
        });

        // Create the AccountHeader
        String name = mFirebaseUser.getDisplayName();
        String email = mFirebaseUser.getEmail();
        Uri imgUri = mFirebaseUser.getPhotoUrl();

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                 .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem().withName(name).withEmail(email).withIcon(imgUri.toString())
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {

                        return false;
                    }
                })
                .build();

        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Home").withIcon(GoogleMaterial.Icon.gmd_home);
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2).withName("Setting").withIcon(GoogleMaterial.Icon.gmd_settings);
        SecondaryDrawerItem item3 = new SecondaryDrawerItem().withIdentifier(3).withName("Logout").withIcon(GoogleMaterial.Icon.gmd_power_settings_new);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_chatList);
        //create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1,
                        new DividerDrawerItem(),
                        item2,
                        item3
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        if (drawerItem.getIdentifier() == 3) {
                            mFirebaseAuth.signOut();
                            checkLogin();
                        }
                        return true;
                    }
                })
                .withAccountHeader(headerResult)
                .build();
        result.getActionBarDrawerToggle().getDrawerArrowDrawable().setColor(Color.WHITE);

        txtTitle = (TextView) findViewById(R.id.tv_chatList_title);
        txtTitle.setText("채팅방 목록");
    }

    private boolean checkLogin() {
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if (mFirebaseUser == null) {

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
            return false;

        } else {
            Log.d("ChatListActivity", mFirebaseUser.getUid());
            return true;
        }
    }

    private void getChatRoomData() {
        adapter = new ChatRoomAdapter(this);
        recyclerView.setAdapter(adapter);

        mRef = mFirebaseDatabase.getReference("User").child(mFirebaseUser.getUid() + "/chatRoomList/");

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot chatroomlistSnapshot : dataSnapshot.getChildren()) {
                    final String roomKey = chatroomlistSnapshot.getKey();

                    DatabaseReference ref = mFirebaseDatabase.getReference("Room").child(roomKey);

                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String roomTitle, roomImageUrl;

                            roomTitle = (String) dataSnapshot.child("roomName").getValue();
                            roomImageUrl = (String) dataSnapshot.child("roomImage").getValue();

                            if (roomTitle != null) {
                                ChatRoomItem item = new ChatRoomItem("", "", "","",roomKey);
                                item.setRoomTitle(roomTitle);
                                item.setRoomImgUrl(roomImageUrl);

                                Log.d(TAG, "AddItem");
                                adapter.addItem(item);
                                adapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.d(TAG, "OnCancelled!");
                        }
                    });
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "onCancelled");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CREATE_REQUEST) {
                getChatRoomData();
            }
            else if (requestCode == SEARCH_REQUEST) {
                getChatRoomData();
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    @OnClick(R.id.testButton)
    public void testClick(){
        Intent intent = new Intent(ChatListActivity.this, ChatBoardActivity.class);
        startActivity(intent);
    }
}
