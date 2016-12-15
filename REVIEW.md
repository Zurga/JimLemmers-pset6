# Review of Jim Lemmers

## Conclusion

Code is clean and written well. Everything is nicely divided in objects. I would just add some comments at the top of some classes 
to explain what they do. The code explains itself, but some classes can be rather big. Also your AuthenticationActivity still has a
comment at the top coming from the Google examples.

## AppCompatPreferenceActivity

Comment at the top says enough, nothing to complain.

## AuthenticationActivity

Some comments are probably leftovers like '//updateUI(user);' at line 79. Comments explain what the activity does, 
but would be handy if there was an overall explanation at the top of the class.

## JSONGetter

Using a seperate JSONGetter to get your data in your AsyncTasks was a smart move. It keeps the code of the rest of the classes
clean.

## MainActivity

Code is commented well and looks good.

## Post

Code explains itself. Just a class to represent a post.

## PostAdapter

Proper implementation of an adapter, I would have put a try, catch around all the assignins of values to the views. In case something went wrong with getting the data.

## PostFragment

Code is clean, nothing to comment.

## ReadFavourites

Nothing to comment.

## ReadStoriesJsonTask

Does exactly what is says. Smart to fill the adapter in the AsyncTask.

## ReadStoryJsonTask

Does what it says, code looks good.

## SettingsActivity

Having a settings activity just to sign in a user is a bit overkill. You could have just used a Fragment or dialog to 
manage the signin/register. Might have made your Activity a bigger mess, but it makes the user experience better.

## authentication

This class appears to be empty, maybe a leftover?

## viewArticle

Simple webview starter, code is clean.
