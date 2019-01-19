<?php
/**
 * Created by PhpStorm.
 * User: Alexandre
 * Date: 18/01/2019
 * Time: 18:24
 */

class Posts implements JsonSerializable
{
    public $post_id, $title, $description, $image, $user_id, $flag_active, $type;

    /**
     * Posts constructor.
     * @param $post_id
     * @param $title
     * @param $description
     * @param $image
     * @param $user_id
     * @param $flag_active
     * @param $type
     */
    public function __construct($post_id, $title, $description, $image, $user_id, $flag_active, $type)
    {
        $this->post_id = $post_id;
        $this->title = $title;
        $this->description = $description;
        $this->image = $image;
        $this->user_id = $user_id;
        $this->flag_active = $flag_active;
        $this->type = $type;
    }

    /**
     * @return mixed
     */
    public function getPostId()
    {
        return $this->post_id;
    }

    /**
     * @param mixed $post_id
     */
    public function setPostId($post_id)
    {
        $this->post_id = $post_id;
    }

    /**
     * @return mixed
     */
    public function getTitle()
    {
        return $this->title;
    }

    /**
     * @param mixed $title
     */
    public function setTitle($title)
    {
        $this->title = $title;
    }

    /**
     * @return mixed
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * @param mixed $description
     */
    public function setDescription($description)
    {
        $this->description = $description;
    }

    /**
     * @return mixed
     */
    public function getImage()
    {
        return $this->image;
    }

    /**
     * @param mixed $image
     */
    public function setImage($image)
    {
        $this->image = $image;
    }

    /**
     * @return mixed
     */
    public function getUserId()
    {
        return $this->user_id;
    }

    /**
     * @param mixed $user_id
     */
    public function setUserId($user_id)
    {
        $this->user_id = $user_id;
    }

    /**
     * @return mixed
     */
    public function getFlagActive()
    {
        return $this->flag_active;
    }

    /**
     * @param mixed $flag_active
     */
    public function setFlagActive($flag_active)
    {
        $this->flag_active = $flag_active;
    }

    /**
     * @return mixed
     */
    public function getType()
    {
        return $this->type;
    }

    /**
     * @param mixed $type
     */
    public function setType($type)
    {
        $this->type = $type;
    }

    public function jsonSerialize()
    {
        return[

            'post_id' => $this->post_id,
            'title' => $this->title,
            'description' => $this->description,
            'image' => $this->image,
            'user_id' => $this->user_id,
            'flag_active' => $this->flag_active,
            'type' => $this->type

        ];

    }


}