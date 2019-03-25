package com.example.bookaih.firebase;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.bookaih.HomePage;
import com.example.bookaih.model.IndividualModel;
import com.example.bookaih.model.MeetModel;
import com.example.bookaih.model.OrderIndividualModel;
import com.example.bookaih.model.OrderWeddingModel;
import com.example.bookaih.model.UserModel;
import com.example.bookaih.model.WeddingModel;
import com.example.bookaih.utils.MyConstant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.bookaih.utils.MyConstant.FIREBASE_INDIVIDUAL;
import static com.example.bookaih.utils.MyConstant.FIREBASE_MEETING;
import static com.example.bookaih.utils.MyConstant.FIREBASE_ORDER;
import static com.example.bookaih.utils.MyConstant.FIREBASE_UPCOMMING;
import static com.example.bookaih.utils.MyConstant.FIREBASE_USER;
import static com.example.bookaih.utils.MyConstant.FIREBASE_WEDDING;

public class FireDatabase {

    Context context;
    private static final String TAG = FireDatabase.class.getSimpleName();
    public DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    public FireAuth fireAuth;
    ProgressDialog progressDialog;


    public FireDatabase(Context context) {
        this.context = context;
        fireAuth = new FireAuth(context);
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("loading...");
        progressDialog.setCancelable(false);
    }

    public FireDatabase(Context context, FireAuth fireAuth) {
        this.context = context;
        this.fireAuth = fireAuth;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("loading...");
    }

   public void addUser(final UserModel user, final ProgressDialog progressDialog) {
        reference.child(FIREBASE_USER).child(user.getId()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success");
                    progressDialog.dismiss();
                    Intent intent = new Intent(context, HomePage.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    ((Activity)context).finish();
                    Toast.makeText(context, "success",
                            Toast.LENGTH_SHORT).show();

                } else {
                    // If sign in fails, display a message to the user.
                    progressDialog.dismiss();
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(context, task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
    public void addIndividual(final IndividualModel model) {
        progressDialog.show();
        String id = reference.push().getKey();
        model.setId(id);
        reference.child(FIREBASE_INDIVIDUAL).child(id).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    progressDialog.dismiss();
                    Toast.makeText(context, "Success",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // If sign in fails, display a message to the user.
                    progressDialog.dismiss();
                    Log.w(TAG, "adding individual:failure", task.getException());
                    Toast.makeText(context, task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
    public void addOrderIndividual(final OrderIndividualModel model) {
        progressDialog.show();
        String id = reference.push().getKey();
        model.setId(id);
        reference.child(FIREBASE_ORDER).child(FIREBASE_INDIVIDUAL).child(id).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    progressDialog.dismiss();
                    Toast.makeText(context, "Success",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // If sign in fails, display a message to the user.
                    progressDialog.dismiss();
                    Log.w(TAG, "adding individual:failure", task.getException());
                    Toast.makeText(context, task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
    public void addOrderWedding(final OrderWeddingModel model) {
        progressDialog.show();
        String id = reference.push().getKey();
        model.setId(id);
        reference.child(FIREBASE_ORDER).child(FIREBASE_WEDDING).child(id).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    progressDialog.dismiss();
                    Toast.makeText(context, "Success",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // If sign in fails, display a message to the user.
                    progressDialog.dismiss();
                    Log.w(TAG, "adding individual:failure", task.getException());
                    Toast.makeText(context, task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    // this method for get all drivers from database
    public void getWedding(final WeddingCallback callback) {
        final ArrayList<WeddingModel> list = new ArrayList<>();
        reference.child(MyConstant.FIREBASE_WEDDING).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        if (snapshot.exists()) {
                            WeddingModel model = snapshot.getValue(WeddingModel.class);
                            list.add(model);
                        }
                    }
                    callback.onCallback(list);
                } else
                    callback.onCallback(list);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("error", databaseError.getMessage());
            }
        });
    }

    // this method for get all drivers from database
    public void getAvaliableIndivid(final IdividualCallback callback) {
        final ArrayList<IndividualModel> list = new ArrayList<>();
        reference.child(MyConstant.FIREBASE_INDIVIDUAL).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        if (snapshot.exists()) {
                            IndividualModel model = snapshot.getValue(IndividualModel.class);
                            list.add(model);
                        }
                    }
                    callback.onCallback(list);
                } else
                    callback.onCallback(list);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("error", databaseError.getMessage());
            }
        });
    }

    public void editIndividual(IndividualModel model) {
        progressDialog.show();
        String id = model.getId();
        reference.child(FIREBASE_INDIVIDUAL).child(id).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    progressDialog.dismiss();
                    Toast.makeText(context, "Success",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // If sign in fails, display a message to the user.
                    progressDialog.dismiss();
                    Log.w(TAG, "editing :failure", task.getException());
                    Toast.makeText(context, task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    public void deleteIndividual(String id) {
        progressDialog.show();
        reference.child(FIREBASE_ORDER).child(FIREBASE_INDIVIDUAL).child(id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    progressDialog.dismiss();
                    Toast.makeText(context, "Deleted!!",
                            Toast.LENGTH_SHORT).show();

                } else {
                    // If sign in fails, display a message to the user.
                    progressDialog.dismiss();
                    Log.w(TAG, "deleting :failure", task.getException());
                    Toast.makeText(context, task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }







    public void addWedding(final WeddingModel model) {
        progressDialog.show();
        String id = reference.push().getKey();
        model.setId(id);
        reference.child(FIREBASE_WEDDING).child(id).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    progressDialog.dismiss();
                    Toast.makeText(context, "Success",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // If sign in fails, display a message to the user.
                    progressDialog.dismiss();
                    Log.w(TAG, "adding individual:failure", task.getException());
                    Toast.makeText(context, task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    public void editWedding(WeddingModel model) {
        progressDialog.show();
        String id = model.getId();
        reference.child(FIREBASE_WEDDING).child(id).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    progressDialog.dismiss();
                    Toast.makeText(context, "Success",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // If sign in fails, display a message to the user.
                    progressDialog.dismiss();
                    Log.w(TAG, "editing :failure", task.getException());
                    Toast.makeText(context, task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    public void deleteWeddig(String id) {
        progressDialog.show();
        reference.child(FIREBASE_ORDER).child(FIREBASE_WEDDING).child(id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    progressDialog.dismiss();
                    Toast.makeText(context, "Deleted!!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // If sign in fails, display a message to the user.
                    progressDialog.dismiss();
                    Log.w(TAG, "deleting :failure", task.getException());
                    Toast.makeText(context, task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



    // this method for get all drivers from database
    public void deleteMeeting(final String id, MeetModel model) {
        progressDialog.show();

        model.setId(id);
        reference.child(FIREBASE_MEETING).child(MyConstant.FIREBASE_CANCELED).child(id).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    progressDialog.show();
                    reference.child(FIREBASE_MEETING).child(FIREBASE_UPCOMMING).child(id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                progressDialog.dismiss();
                                Toast.makeText(context, "Deleted!!",
                                        Toast.LENGTH_SHORT).show();
                                ((Activity)context).finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                progressDialog.dismiss();
                                Log.w(TAG, "deleting :failure", task.getException());
                                Toast.makeText(context, task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
//                    Toast.makeText(context, "Success",
//                            Toast.LENGTH_SHORT).show();
//                    ((Activity)context).finish();
                } else {
                    // If sign in fails, display a message to the user.
                    progressDialog.dismiss();
                    Log.w(TAG, "adding :failure", task.getException());
                    Toast.makeText(context, task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();

                }

            }
        });

    }
 // this method for get all drivers from database
    public void getMeetingsCanceld(final MeetingCallback callback) {
        final ArrayList<MeetModel> list = new ArrayList<>();
        reference.child(FIREBASE_MEETING).child(MyConstant.FIREBASE_CANCELED).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        if (snapshot.exists()) {
                            MeetModel model = snapshot.getValue(MeetModel.class);
                            list.add(model);
                        }
                    }
                    callback.onCallback(list);
                } else
                    callback.onCallback(list);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("error", databaseError.getMessage());
            }
        });
    }
 // this method for get all drivers from database
    public void getMeetingsUpcomming(final MeetingCallback callback) {
        final ArrayList<MeetModel> list = new ArrayList<>();
        reference.child(FIREBASE_MEETING).child(MyConstant.FIREBASE_UPCOMMING).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        if (snapshot.exists()) {
                            MeetModel model = snapshot.getValue(MeetModel.class);
                            list.add(model);
                        }
                    }
                    callback.onCallback(list);
                } else
                    callback.onCallback(list);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("error", databaseError.getMessage());
            }
        });
    }

    // this method for get all drivers from database
    public void getUser(String userId,final UserCallback callback) {
        reference.child(MyConstant.FIREBASE_USER).child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                        UserModel model = dataSnapshot.getValue(UserModel.class);
                    callback.onCallback(model);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("error", databaseError.getMessage());
            }
        });
    }

    // this method for get all drivers from database
    public void getIndiviualProduct(String productId,final IndividualProductCallback callback) {
        reference.child(MyConstant.FIREBASE_INDIVIDUAL).child(productId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    IndividualModel model = dataSnapshot.getValue(IndividualModel.class);
                    callback.onCallback(model);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("error", databaseError.getMessage());
            }
        });
    }


    public void addMeeting(final MeetModel model) {
        progressDialog.show();
        String id = reference.push().getKey();
        model.setId(id);
        reference.child(FIREBASE_MEETING).child(MyConstant.FIREBASE_UPCOMMING).child(id).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    progressDialog.dismiss();
                    Toast.makeText(context, "Success",
                            Toast.LENGTH_SHORT).show();
                    ((Activity)context).finish();
                } else {
                    // If sign in fails, display a message to the user.
                    progressDialog.dismiss();
                    Log.w(TAG, "adding :failure", task.getException());
                    Toast.makeText(context, task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    // this method for get all drivers from database
    public void getWeddingProduct(String productId,final WeddingProductCallback callback) {
        reference.child(MyConstant.FIREBASE_WEDDING).child(productId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    WeddingModel model = dataSnapshot.getValue(WeddingModel.class);
                    callback.onCallback(model);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("error", databaseError.getMessage());
            }
        });
    }




    // this method for get all drivers from database
    public void getWeddingOrders(final OrderWeddingCallback callback) {
        final ArrayList<OrderWeddingModel> list = new ArrayList<>();
        reference.child(MyConstant.FIREBASE_ORDER).child(MyConstant.FIREBASE_WEDDING).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        if (snapshot.exists()) {
                            OrderWeddingModel model = snapshot.getValue(OrderWeddingModel.class);
                            list.add(model);
                        }
                    }
                    callback.onCallback(list);
                } else
                    callback.onCallback(list);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("error", databaseError.getMessage());
            }
        });
    }

    // this method for get all drivers from database
    public void getIndividualOrders(final OrderIndividualCallback callback) {
        final ArrayList<OrderIndividualModel> list = new ArrayList<>();
        reference.child(MyConstant.FIREBASE_ORDER).child(MyConstant.FIREBASE_INDIVIDUAL).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        if (snapshot.exists()) {
                            OrderIndividualModel model = snapshot.getValue(OrderIndividualModel.class);
                            list.add(model);
                        }
                    }
                    callback.onCallback(list);
                } else
                    callback.onCallback(list);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("error", databaseError.getMessage());
            }
        });
    }



    public interface IdividualCallback {
        void onCallback(ArrayList<IndividualModel> list);
    }
    public interface WeddingCallback {
        void onCallback(ArrayList<WeddingModel> list);
    }

    public interface OrderWeddingCallback {
        void onCallback(ArrayList<OrderWeddingModel> list);
    }

    public interface OrderIndividualCallback {
        void onCallback(ArrayList<OrderIndividualModel> list);
    }

    public interface MeetingCallback {
        void onCallback(ArrayList<MeetModel> list);
    }

    public interface WeddingProductCallback {
        void onCallback(WeddingModel model);
    }

    public interface IndividualProductCallback {
        void onCallback(IndividualModel model);
    }

    public interface OneOrderWeddingCallback {
        void onCallback(OrderWeddingModel model);
    }

    public interface OneOrderIndividualCallback {
        void onCallback(OrderIndividualModel model);
    }

    public interface UserCallback {
        void onCallback(UserModel model);
    }

    public interface OneMeetCallback {
        void onCallback(MeetModel model);
    }




}
