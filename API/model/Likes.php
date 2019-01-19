<?php
/**
 * Created by PhpStorm.
 * User: Alexandre
 * Date: 18/01/2019
 * Time: 18:24
 */

class Likes implements JsonSerializable
{

    public $like_id, $post_id, $user_id;

    /**
     * Likes constructor.
     * @param $like_id
     * @param $post_id
     * @param $user_id
     */
    public function __construct($like_id, $post_id, $user_id)
    {
        $this->like_id = $like_id;
        $this->post_id = $post_id;
        $this->user_id = $user_id;
    }

    /**
     * @return mixed
     */
    public function getLikeId()
    {
        return $this->like_id;
    }

    /**
     * @param mixed $like_id
     */
    public function setLikeId($like_id)
    {
        $this->like_id = $like_id;
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

    public function jsonSerialize()
    {
        return[
            'like_id' => $this->like_id,
            'post_id' => $this->post_id,
            'user_id' => $this->user_id

        ];
    }


}