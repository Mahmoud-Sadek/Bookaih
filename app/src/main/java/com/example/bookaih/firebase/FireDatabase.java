package com.example.bookaih.firebase;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.bookaih.HomePage;
import com.example.bookaih.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.bookaih.utils.MyConstant.FIREBASE_USER;

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
 /*
    public void editUser(final UserModel user,final ProgressDialog progressDialog) {
        reference.child(FIREBASE_USER).child(user.getId()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success");
                    progressDialog.dismiss();
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
    public void editUser(final UserModel user) {
        progressDialog.show();
        reference.child(FIREBASE_USER).child(user.getId()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success");
                    progressDialog.dismiss();
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

    public void addCourse(final CourseModel model) {
        progressDialog.show();
        String id = reference.push().getKey();
        model.setId(id);
        reference.child(FIREBASE_COURSES).child(id).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    progressDialog.dismiss();
                    Intent intent = new Intent(context, MyCoursesActivity.class);
                    context.startActivity(intent);
                    Toast.makeText(context, "Course Added",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // If sign in fails, display a message to the user.
                    progressDialog.dismiss();
                    Log.w(TAG, "adding course:failure", task.getException());
                    Toast.makeText(context, task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    public void addVideo(final String courseId, final VideoModel model) {
        progressDialog.show();
        String id = reference.push().getKey();
        model.setId(id);
        reference.child(FIREBASE_COURSE_VIDEOS).child(courseId).child(id).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    progressDialog.dismiss();
                    Intent intent = new Intent(context, MyCoursesActivity.class);
                    context.startActivity(intent);
                    ((Activity)context).finish();
                    Toast.makeText(context, "Video Added",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // If sign in fails, display a message to the user.
                    progressDialog.dismiss();
                    Log.w(TAG, "adding course:failure", task.getException());
                    Toast.makeText(context, task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
    public void editVideo(String courseId, final VideoModel model) {
        progressDialog.show();
        String id = model.getId();
        reference.child(FIREBASE_COURSE_VIDEOS).child(courseId).child(id).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    progressDialog.dismiss();
                    Intent intent = new Intent(context, MyCoursesActivity.class);
                    context.startActivity(intent);
                    Toast.makeText(context, "Edit Success",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // If sign in fails, display a message to the user.
                    progressDialog.dismiss();
                    Log.w(TAG, "adding course:failure", task.getException());
                    Toast.makeText(context, task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    public void deleteCourse(final String id) {
        progressDialog.show();
        reference.child(FIREBASE_COURSES).child(id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    reference.child(FIREBASE_COURSE_VIDEOS).child(id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                progressDialog.dismiss();
                                Intent intent = new Intent(context, MyCoursesActivity.class);
                                context.startActivity(intent);
                                Toast.makeText(context, "Course Deleted!!",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                // If sign in fails, display a message to the user.
                                progressDialog.dismiss();
                                Log.w(TAG, "deleting course:failure", task.getException());
                                Toast.makeText(context, task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    // If sign in fails, display a message to the user.
                    progressDialog.dismiss();
                    Log.w(TAG, "deleting course:failure", task.getException());
                    Toast.makeText(context, task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void deleteVideo(final String courseId, String id) {
        progressDialog.show();
        reference.child(FIREBASE_COURSE_VIDEOS).child(courseId).child(id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    progressDialog.dismiss();
                    Toast.makeText(context, "Video Deleted!!",
                            Toast.LENGTH_SHORT).show();
                    ((Activity)context).finish();
                } else {
                    // If sign in fails, display a message to the user.
                    progressDialog.dismiss();
                    Log.w(TAG, "deleting Video:failure", task.getException());
                    Toast.makeText(context, task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void editCourse(final CourseModel model) {
        progressDialog.show();
        reference.child(FIREBASE_COURSES).child(model.getId()).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    progressDialog.dismiss();
                    Intent intent = new Intent(context, MyCoursesActivity.class);
                    context.startActivity(intent);
                    ((Activity)context).finish();
                    Toast.makeText(context, "Success",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // If sign in fails, display a message to the user.
                    progressDialog.dismiss();
                    Log.w(TAG, "editing course:failure", task.getException());
                    Toast.makeText(context, task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();

                }

            }
        });
    }


    public void joinCourse(String userID, final CourseModel model) {
        progressDialog.show();
        String id = model.getId();
        reference.child(FIREBASE_JOINED_COURSE).child(userID).child(id).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    progressDialog.dismiss();
                    Toast.makeText(context, "Joined success",
                            Toast.LENGTH_LONG).show();

                } else {
                    // If sign in fails, display a message to the user.
                    progressDialog.dismiss();
                    Log.w(TAG, "joined:failure", task.getException());
                    Toast.makeText(context, task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    // this method for get specified user by id from database
    public void getUserById(String id, final UserCallback callback) {
        if (id == null | id.isEmpty()) {
            return;
        }
        reference.child(MyConstant.FIREBASE_USER).child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserModel model = new UserModel();
                if (dataSnapshot.exists()) {
                    model = dataSnapshot.getValue(UserModel.class);
                    callback.onCallback(model);
                } else
                    callback.onCallback(model);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("error", databaseError.getMessage());
            }
        });
    }


    // this method for get specified course by id from database
    public void checkCourseJoined(String id, final CheckJoinedCourseCallback callback) {
        if (id == null | id.isEmpty()) {
            return;
        }
        reference.child(MyConstant.FIREBASE_JOINED_COURSE).child(fireAuth.mAuth.getCurrentUser().getUid()).child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    callback.onCallback(true);
                } else
                    callback.onCallback(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("error", databaseError.getMessage());
            }
        });
    }


    // this method for get all drivers from database
    public void getAvaliableCourses(final CoursesCallback callback) {
        final ArrayList<CourseModel> list = new ArrayList<>();
        reference.child(MyConstant.FIREBASE_COURSES).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        if (snapshot.exists()) {
                            CourseModel model = snapshot.getValue(CourseModel.class);
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
    public void getJoinedCourses(final String userId, final CoursesCallback callback) {
        final ArrayList<CourseModel> list = new ArrayList<>();

        reference.child(MyConstant.FIREBASE_JOINED_COURSE).child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        if (snapshot.exists()) {
                            final CourseModel model = snapshot.getValue(CourseModel.class);
                            list.add(model);
//                            reference.child(MyConstant.FIREBASE_COURSES).child(model.getId()).addValueEventListener(new ValueEventListener() {
//                                @Override
//                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                    if (dataSnapshot.exists()) {
//                                        list.add(model);
//                                    } else {
//                                        reference.child(MyConstant.FIREBASE_JOINED_COURSE).child(userId).child(model.getId()).removeValue();
//                                    }
//                                }
//
//                                @Override
//                                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                                }
//                            })
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


    // this method for get all Courses from database
    public void getMyCourses(final String userId, final CoursesCallback callback) {
        if (userId == null && userId.isEmpty()) {
            return;
        }
        final ArrayList<CourseModel> list = new ArrayList<>();
        reference.child(MyConstant.FIREBASE_COURSES).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        if (snapshot.exists()) {
                            CourseModel model = snapshot.getValue(CourseModel.class);
                            if (model.getProviderId().equals(userId))
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


    // this method for get all videos from database
    public void getVideos(final String courseId, final VideoCallback callback) {
        if (courseId == null && courseId.isEmpty()) {
            return;
        }
        final ArrayList<VideoModel> list = new ArrayList<>();
        reference.child(MyConstant.FIREBASE_COURSE_VIDEOS).child(courseId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        if (snapshot.exists()) {
                            VideoModel model = snapshot.getValue(VideoModel.class);
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

    // this method for get User Courses from database
    public void getUserCourses(final CoursesCallback callback) {
        final ArrayList<CourseModel> list = new ArrayList<>();
        reference.child(MyConstant.FIREBASE_COURSES).child(fireAuth.mAuth.getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                if (snapshot.exists()) {
                                    CourseModel model = snapshot.getValue(CourseModel.class);
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









    //this callback for get only one user
    public interface CoursesCallback {
        void onCallback(ArrayList<CourseModel> list);
    }

    //this callback for get only one user
    public interface VideoCallback {
        void onCallback(ArrayList<VideoModel> list);
    }

    //this callback for get only one Course
    public interface CourseCallback {
        void onCallback(CourseModel model);
    }

    public interface CheckJoinedCourseCallback {
        void onCallback(boolean isFound);
    }

    //this callback for get only one user
    public interface UserCallback {
        void onCallback(UserModel model);
    }
*/
}
