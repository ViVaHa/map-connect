// This file contains the sequence of steps that a welcome tour shows
var tour = new Tour({
    smartPlacement: true,
    name: "tour",
    steps: [{
        element: "#map",
        title: "<strong>Map of the world</strong>",
        content: "Feel free to drag around. Double click to add a circle"
      },
      {
        element: "#toggleCirclePanelBtn",
        title: "<strong>Circles panel</strong>",
        content: "Toggle visibility of the circle panels."
      },
      {
        element: "#circleLabelDiv",
        title: "<strong>Added circle panels</strong>",
        content: "The circles you add here would be displayed here"
      },
      {
        element: "#circleLabelDiv",
        title: "<strong>To point to the circle</strong>",
        content: "Hover over a label. Try hovering over multiple labels"
      },
      {
        element: "#deleteCircleLabel",
        title: "<strong>Delete</strong>",
        content: "Press the <span class='glyphicon glyphicon-trash'></span> icon to delete the circle. You can also double click inside the circle to delete it"
      },
      {
      element: "#showCircleUserCount",
      title: "<strong>List of Users</strong>",
      content: "Displays the count of users to which the mail has been sent in this circle"
      },
      {
        element: "#sendEmailBtn",
        title: "<strong>Send Email</strong>",
        content: "To send emails to user in this region, click here"
      },
    ]
  });
