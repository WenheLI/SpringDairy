# This is a Restful API for backend

## User can query, add and delete corresponding items.

-     GET /dairy/?id& description

- If two of the paramaters are null, it return all dairies. If we have description
it will query for description. If there is only, it will query by id.

-     POST /dairy/

- Post with date, description(nullable) and calories

-     Delete /dairy/id

- Delete the item with corresponding id.

-     GET /dairy/days/

-  It will return the amount of calories by date.