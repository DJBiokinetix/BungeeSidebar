BungeeSidebar
=============

Sidebar announcer for BungeeCord

Please see config file file for settings example.

====================================================

Commands:

/sb freeze		- freeze the current sidebar

/sb unfreeze 	- undo freeze

/sb reload    - safe config reload (unloading it directly can cause unpredictable results including clients crash)

/sb frozen		- tells you if the current sidebar is frozen or not


==============================================================

Permissions:

sb.use		- For all sb commands

================================================================

config.txt:


delay=60    //Delay in seconds between one sidebar message and another

random=true //If the order should be randomized



[excludeservers] //Insert names of servers on which you would not want to see the sidebars

fun

auth

[/excludeservers]


//Example message

// Valid tags:

//  name=&lt;name&gt;     - Sets the name for the sidebar, allowing you to set this sidebar using /sb set <name>.

//                    If you do not provide this information it will be set to random, starting with @_

//  title=&lt;title&gt;   - Sets the title of sidebar. Note the 32 characters limit

//  excluded=&lt;true&gt; - If set to true, the message will never be shown, unless you use /sb set


// The text no enclosed in any of the tags will be ignored. (comments)

[msg]title=§aExample,name=example

line 1

line 2

line 3

[/msg]
