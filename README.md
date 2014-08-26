BungeeSidebar
=============

Sidebar announcer for BungeeCord

Please see config file file for settings example.

====================================================

config.txt:


delay=60    //Delay in seconds between one sidebar message and another

random=true //If the order should be randomized



[excludeservers] //Insert names of servers on which you would not want to see the sidebars

fun

auth

[/excludeservers]


//Example message

// Valid tags:

//  name=<name>     - Sets the name for the sidebar, allowing you to set this sidebar using /sb set <name>.

//                    If you do not provide this information it will be set to random, starting with @_

//  title=<title>   - Sets the title of sidebar. Note the 32 characters limit

//  excluded=<true> - If set to true, the message will never be shown, unless you use /sb set


// The text no enclosed in any of the tags will be ignored. (comments)

[msg]title=§aExample,name=example

line 1

line 2

line 3

[/msg]
