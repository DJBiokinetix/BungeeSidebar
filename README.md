BungeeSidebar
=============

Sidebar announcer for BungeeCord

Please see config file file for settings example.

====================================================

#### Commands:


/sb freeze		- freeze the current <br />
/sb unfreeze 	- undo freeze<br />
/sb reload    - safe config reload (unloading it directly can cause unpredictable results including clients crash)<br />
/sb frozen		- tells you if the current sidebar is frozen or not<br />


==============================================================

####Permissions:<br />
sb.use		- For all sb commands

================================================================

#### config.txt:


`delay=60`    //Delay in seconds between one sidebar message and another<br />
`random=true` //If the order should be randomized<br />

`[excludeservers]` //Insert names of servers on which you would not want to see the sidebars<br />
`fun`<br />
`auth`<br />
`[/excludeservers]`<br />

//Example message<br />
// Valid tags:<br />
//  `name=&lt;name&gt`;     - Sets the name for the sidebar, allowing you to set this sidebar using /sb set <name>.<br />
//                    If you do not provide this information it will be set to random, starting with @_<br />
//  `title=&lt;title&gt;`   - Sets the title of sidebar. Note the 32 characters limit<br />
//  `excluded=&lt;true&gt;` - If set to true, the message will never be shown, unless you use /sb set<br />
// The text no enclosed in any of the tags will be ignored. (comments)<br />

`[msg]title=§aExample,name=example`<br />
`line 1`<br />
`line 2`<br />
`line 3`<br />
`[/msg]`<br />
